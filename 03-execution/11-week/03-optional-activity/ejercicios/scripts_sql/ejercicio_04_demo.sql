DO $$
DECLARE
    v_loyalty_account_id uuid;
    v_ticket_segment_id uuid;
BEGIN
    SELECT la.loyalty_account_id
    INTO v_loyalty_account_id
    FROM loyalty_account la
    ORDER BY la.created_at
    LIMIT 1;

    SELECT ts.ticket_segment_id
    INTO v_ticket_segment_id
    FROM ticket_segment ts
    ORDER BY ts.created_at
    LIMIT 1;

    IF v_loyalty_account_id IS NULL THEN
        RAISE EXCEPTION 'No existe loyalty_account disponible.';
    END IF;

    CALL sp_accumulate_miles(
        v_loyalty_account_id,
        1000.00,
        v_ticket_segment_id
    );
END;
$$;