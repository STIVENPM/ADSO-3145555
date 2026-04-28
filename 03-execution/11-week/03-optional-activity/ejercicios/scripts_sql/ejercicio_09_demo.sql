DO $$
DECLARE
    v_fare_class_id uuid;
    v_currency_id uuid;
BEGIN
    SELECT fc.fare_class_id
    INTO v_fare_class_id
    FROM fare_class fc
    ORDER BY fc.created_at
    LIMIT 1;

    SELECT c.currency_id
    INTO v_currency_id
    FROM currency c
    ORDER BY c.created_at
    LIMIT 1;

    CALL sp_publish_fare(
        'FARE001',
        v_fare_class_id,
        200.00,
        v_currency_id
    );
END;
$$;