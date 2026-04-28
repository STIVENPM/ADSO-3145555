DROP TRIGGER IF EXISTS trg_ai_flight_delay_update_status ON flight_delay;
DROP FUNCTION IF EXISTS fn_ai_flight_delay_update_status();
DROP PROCEDURE IF EXISTS sp_report_delay(uuid, uuid, integer, varchar);

CREATE OR REPLACE FUNCTION fn_ai_flight_delay_update_status()
RETURNS trigger
LANGUAGE plpgsql
AS $$
DECLARE
    v_delayed_status_id uuid;
BEGIN
    -- Update flight status to delayed
    SELECT fs.flight_status_id
    INTO v_delayed_status_id
    FROM flight_status fs
    WHERE fs.status_name = 'Delayed'
    LIMIT 1;

    IF v_delayed_status_id IS NOT NULL THEN
        UPDATE flight
        SET flight_status_id = v_delayed_status_id
        WHERE flight_id = NEW.flight_id;
    END IF;

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_ai_flight_delay_update_status
AFTER INSERT ON flight_delay
FOR EACH ROW
EXECUTE FUNCTION fn_ai_flight_delay_update_status();

CREATE OR REPLACE PROCEDURE sp_report_delay(
    p_flight_id uuid,
    p_delay_reason_type_id uuid,
    p_delay_minutes integer,
    p_delay_description varchar(255)
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO flight_delay (
        flight_id,
        delay_reason_type_id,
        delay_minutes,
        delay_description
    )
    VALUES (
        p_flight_id,
        p_delay_reason_type_id,
        p_delay_minutes,
        p_delay_description
    );
END;
$$;

-- Consulta resuelta: retrasos operativos y impacto
SELECT
    f.flight_number,
    f.service_date,
    fs.segment_number,
    fd.delay_minutes,
    drt.reason_name,
    fd.delay_description,
    ts.ticket_segment_id,
    a.aircraft_code
FROM flight f
INNER JOIN flight_segment fs
    ON fs.flight_id = f.flight_id
INNER JOIN flight_delay fd
    ON fd.flight_id = f.flight_id
INNER JOIN delay_reason_type drt
    ON drt.delay_reason_type_id = fd.delay_reason_type_id
INNER JOIN ticket_segment ts
    ON ts.flight_segment_id = fs.flight_segment_id
INNER JOIN aircraft a
    ON a.aircraft_id = f.aircraft_id
ORDER BY fd.delay_minutes DESC;