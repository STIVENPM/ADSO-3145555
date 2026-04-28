DROP TRIGGER IF EXISTS trg_ai_payment_transaction_create_refund ON payment_transaction;
DROP FUNCTION IF EXISTS fn_ai_payment_transaction_create_refund();
DROP PROCEDURE IF EXISTS sp_register_payment_transaction(uuid, varchar, numeric, uuid, timestamptz);

CREATE OR REPLACE FUNCTION fn_ai_payment_transaction_create_refund()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
    IF NEW.transaction_type = 'refund' THEN
        INSERT INTO refund (
            payment_id,
            refund_amount,
            refund_reason,
            processed_at
        )
        VALUES (
            NEW.payment_id,
            NEW.amount,
            'Automated refund from transaction',
            NEW.transaction_date
        );
    END IF;

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_ai_payment_transaction_create_refund
AFTER INSERT ON payment_transaction
FOR EACH ROW
EXECUTE FUNCTION fn_ai_payment_transaction_create_refund();

CREATE OR REPLACE PROCEDURE sp_register_payment_transaction(
    p_payment_id uuid,
    p_transaction_type varchar(50),
    p_amount numeric(10,2),
    p_currency_id uuid,
    p_transaction_date timestamptz
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO payment_transaction (
        payment_id,
        transaction_type,
        amount,
        currency_id,
        transaction_date
    )
    VALUES (
        p_payment_id,
        p_transaction_type,
        p_amount,
        p_currency_id,
        p_transaction_date
    );
END;
$$;

-- Consulta resuelta: trazabilidad de pagos y transacciones financieras
SELECT
    s.sale_code,
    r.reservation_code,
    p.payment_reference,
    ps.status_name,
    pm.method_name,
    pt.transaction_reference,
    pt.transaction_type,
    pt.amount,
    c.currency_code
FROM sale s
INNER JOIN reservation r
    ON r.reservation_id = s.reservation_id
INNER JOIN payment p
    ON p.sale_id = s.sale_id
INNER JOIN payment_status ps
    ON ps.payment_status_id = p.payment_status_id
INNER JOIN payment_method pm
    ON pm.payment_method_id = p.payment_method_id
INNER JOIN payment_transaction pt
    ON pt.payment_id = p.payment_id
INNER JOIN currency c
    ON c.currency_id = p.currency_id
ORDER BY s.created_at DESC;