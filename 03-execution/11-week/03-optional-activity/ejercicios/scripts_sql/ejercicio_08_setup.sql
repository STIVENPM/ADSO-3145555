DROP TRIGGER IF EXISTS trg_ai_user_role_grant_permissions ON user_role;
DROP FUNCTION IF EXISTS fn_ai_user_role_grant_permissions();
DROP PROCEDURE IF EXISTS sp_assign_role(uuid, uuid);

CREATE OR REPLACE FUNCTION fn_ai_user_role_grant_permissions()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
    -- Automatically grant permissions based on role
    -- For simplicity, assume permissions are already linked via role_permission
    -- Could insert into a permission log or validate
    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_ai_user_role_grant_permissions
AFTER INSERT ON user_role
FOR EACH ROW
EXECUTE FUNCTION fn_ai_user_role_grant_permissions();

CREATE OR REPLACE PROCEDURE sp_assign_role(
    p_user_account_id uuid,
    p_security_role_id uuid
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO user_role (
        user_account_id,
        security_role_id,
        assigned_at
    )
    VALUES (
        p_user_account_id,
        p_security_role_id,
        now()
    );
END;
$$;

-- Consulta resuelta: auditoría de acceso y roles de usuarios
SELECT
    ua.username,
    ua.created_at,
    sr.role_name,
    ur.assigned_at,
    rp.security_permission_id,
    sp.permission_name
FROM user_account ua
INNER JOIN user_role ur
    ON ur.user_account_id = ua.user_account_id
INNER JOIN security_role sr
    ON sr.security_role_id = ur.security_role_id
INNER JOIN role_permission rp
    ON rp.security_role_id = sr.security_role_id
INNER JOIN security_permission sp
    ON sp.security_permission_id = rp.security_permission_id
ORDER BY ua.created_at DESC;