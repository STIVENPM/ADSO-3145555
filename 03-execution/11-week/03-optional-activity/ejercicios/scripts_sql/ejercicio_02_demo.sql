DO $$
DECLARE
    v_payment_id uuid;
    v_currency_id uuid;
BEGIN
    SELECT p.payment_id
    INTO v_payment_id
    FROM payment p
    ORDER BY p.created_at DESC
    LIMIT 1;

    SELECT c.currency_id
    INTO v_currency_id
    FROM currency c
    ORDER BY c.created_at
    LIMIT 1;

    IF v_payment_id IS NULL THEN
        RAISE EXCEPTION 'No existe payment disponible.';
    END IF;

    CALL sp_register_payment_transaction(
        v_payment_id,
        'refund',
        100.00,
        v_currency_id,
        now()
    );
END;
$$;