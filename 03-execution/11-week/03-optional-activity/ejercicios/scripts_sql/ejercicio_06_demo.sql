DO $$
DECLARE
    v_flight_id uuid;
    v_delay_reason_type_id uuid;
BEGIN
    SELECT f.flight_id
    INTO v_flight_id
    FROM flight f
    ORDER BY f.created_at DESC
    LIMIT 1;

    SELECT drt.delay_reason_type_id
    INTO v_delay_reason_type_id
    FROM delay_reason_type drt
    ORDER BY drt.created_at
    LIMIT 1;

    IF v_flight_id IS NULL THEN
        RAISE EXCEPTION 'No existe flight disponible.';
    END IF;

    CALL sp_report_delay(
        v_flight_id,
        v_delay_reason_type_id,
        30,
        'Weather delay'
    );
END;
$$;