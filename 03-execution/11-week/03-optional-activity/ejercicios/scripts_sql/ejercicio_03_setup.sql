DROP TRIGGER IF EXISTS trg_ai_invoice_line_update_tax ON invoice_line;
DROP FUNCTION IF EXISTS fn_ai_invoice_line_update_tax();
DROP PROCEDURE IF EXISTS sp_add_invoice_line(uuid, varchar, numeric, uuid);

CREATE OR REPLACE FUNCTION fn_ai_invoice_line_update_tax()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
    -- Automate tax calculation or validation
    -- For simplicity, ensure tax is applied
    IF NEW.tax_id IS NOT NULL THEN
        -- Could update line_amount with tax, but no field
        -- Just validate
        IF NOT EXISTS (SELECT 1 FROM tax WHERE tax_id = NEW.tax_id) THEN
            RAISE EXCEPTION 'Invalid tax_id';
        END IF;
    END IF;

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_ai_invoice_line_update_tax
AFTER INSERT ON invoice_line
FOR EACH ROW
EXECUTE FUNCTION fn_ai_invoice_line_update_tax();

CREATE OR REPLACE PROCEDURE sp_add_invoice_line(
    p_invoice_id uuid,
    p_line_description varchar(255),
    p_line_amount numeric(10,2),
    p_tax_id uuid
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO invoice_line (
        invoice_id,
        line_description,
        line_amount,
        tax_id
    )
    VALUES (
        p_invoice_id,
        p_line_description,
        p_line_amount,
        p_tax_id
    );
END;
$$;

-- Consulta resuelta: relación entre venta, factura, líneas e impuestos
SELECT
    s.sale_code,
    i.invoice_number,
    ist.status_name,
    il.line_description,
    il.line_amount,
    t.tax_name,
    t.tax_rate,
    c.currency_code
FROM sale s
INNER JOIN invoice i
    ON i.sale_id = s.sale_id
INNER JOIN invoice_status ist
    ON ist.invoice_status_id = i.invoice_status_id
INNER JOIN invoice_line il
    ON il.invoice_id = i.invoice_id
INNER JOIN tax t
    ON t.tax_id = il.tax_id
INNER JOIN currency c
    ON c.currency_id = i.currency_id
ORDER BY i.created_at DESC;