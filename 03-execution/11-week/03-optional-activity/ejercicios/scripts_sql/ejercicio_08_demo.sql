DO $$
DECLARE
    v_user_account_id uuid;
    v_security_role_id uuid;
BEGIN
    SELECT ua.user_account_id
    INTO v_user_account_id
    FROM user_account ua
    ORDER BY ua.created_at
    LIMIT 1;

    SELECT sr.security_role_id
    INTO v_security_role_id
    FROM security_role sr
    ORDER BY sr.created_at
    LIMIT 1;

    IF v_user_account_id IS NULL THEN
        RAISE EXCEPTION 'No existe user_account disponible.';
    END IF;

    CALL sp_assign_role(
        v_user_account_id,
        v_security_role_id
    );
END;
$$;