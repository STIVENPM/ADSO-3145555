DROP TRIGGER IF EXISTS trg_ai_fare_validate ON fare;
DROP FUNCTION IF EXISTS fn_ai_fare_validate();
DROP PROCEDURE IF EXISTS sp_publish_fare(varchar, uuid, numeric, uuid);

CREATE OR REPLACE FUNCTION fn_ai_fare_validate()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
    -- Validate fare amount > 0
    IF NEW.amount <= 0 THEN
        RAISE EXCEPTION 'Fare amount must be positive';
    END IF;

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_ai_fare_validate
AFTER INSERT ON fare
FOR EACH ROW
EXECUTE FUNCTION fn_ai_fare_validate();

CREATE OR REPLACE PROCEDURE sp_publish_fare(
    p_fare_code varchar(20),
    p_fare_class_id uuid,
    p_amount numeric(10,2),
    p_currency_id uuid
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO fare (
        fare_code,
        fare_class_id,
        amount,
        currency_id,
        valid_from,
        valid_to
    )
    VALUES (
        p_fare_code,
        p_fare_class_id,
        p_amount,
        p_currency_id,
        now(),
        now() + interval '1 year'
    );
END;
$$;

-- Consulta resuelta: publicación de tarifas y reservas
SELECT
    f.fare_code,
    f.amount,
    fc.class_name,
    r.reservation_code,
    t.ticket_number,
    fs.segment_number,
    c.currency_code
FROM fare f
INNER JOIN fare_class fc
    ON fc.fare_class_id = f.fare_class_id
INNER JOIN ticket t
    ON t.fare_id = f.fare_id
INNER JOIN reservation_passenger rp
    ON rp.reservation_passenger_id = t.reservation_passenger_id
INNER JOIN reservation r
    ON r.reservation_id = rp.reservation_id
INNER JOIN ticket_segment ts
    ON ts.ticket_id = t.ticket_id
INNER JOIN flight_segment fs
    ON fs.flight_segment_id = ts.flight_segment_id
INNER JOIN currency c
    ON c.currency_id = f.currency_id
ORDER BY f.created_at DESC;