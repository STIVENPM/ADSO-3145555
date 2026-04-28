DO $$
DECLARE
    v_invoice_id uuid;
    v_tax_id uuid;
BEGIN
    SELECT i.invoice_id
    INTO v_invoice_id
    FROM invoice i
    ORDER BY i.created_at DESC
    LIMIT 1;

    SELECT t.tax_id
    INTO v_tax_id
    FROM tax t
    ORDER BY t.created_at
    LIMIT 1;

    IF v_invoice_id IS NULL THEN
        RAISE EXCEPTION 'No existe invoice disponible.';
    END IF;

    CALL sp_add_invoice_line(
        v_invoice_id,
        'Servicio de vuelo',
        500.00,
        v_tax_id
    );
END;
$$;