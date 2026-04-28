DROP TRIGGER IF EXISTS trg_ai_maintenance_event_update_status ON maintenance_event;
DROP FUNCTION IF EXISTS fn_ai_maintenance_event_update_status();
DROP PROCEDURE IF EXISTS sp_schedule_maintenance(uuid, uuid, uuid, timestamptz);

CREATE OR REPLACE FUNCTION fn_ai_maintenance_event_update_status()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
    -- Automate status update, e.g., set aircraft as under maintenance
    -- For simplicity, assume a status update if field exists, else just validate
    -- Since no status field, perhaps insert a delay for affected flights
    INSERT INTO flight_delay (
        flight_id,
        delay_reason_type_id,
        delay_minutes,
        delay_description
    )
    SELECT 
        f.flight_id,
        (SELECT drt.delay_reason_type_id FROM delay_reason_type drt WHERE drt.reason_name = 'Maintenance' LIMIT 1),
        60,
        'Maintenance scheduled'
    FROM flight f
    WHERE f.aircraft_id = NEW.aircraft_id
    AND f.service_date >= NEW.scheduled_date;

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_ai_maintenance_event_update_status
AFTER INSERT ON maintenance_event
FOR EACH ROW
EXECUTE FUNCTION fn_ai_maintenance_event_update_status();

CREATE OR REPLACE PROCEDURE sp_schedule_maintenance(
    p_aircraft_id uuid,
    p_maintenance_type_id uuid,
    p_maintenance_provider_id uuid,
    p_scheduled_date timestamptz
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO maintenance_event (
        aircraft_id,
        maintenance_type_id,
        maintenance_provider_id,
        scheduled_date,
        status
    )
    VALUES (
        p_aircraft_id,
        p_maintenance_type_id,
        p_maintenance_provider_id,
        p_scheduled_date,
        'scheduled'
    );
END;
$$;

-- Consulta resuelta: mantenimiento de aeronaves y operaciones
SELECT
    a.aircraft_code,
    me.scheduled_date,
    me.status,
    mt.type_name,
    mp.provider_name,
    f.flight_number,
    f.service_date
FROM aircraft a
INNER JOIN maintenance_event me
    ON me.aircraft_id = a.aircraft_id
INNER JOIN maintenance_type mt
    ON mt.maintenance_type_id = me.maintenance_type_id
INNER JOIN maintenance_provider mp
    ON mp.maintenance_provider_id = me.maintenance_provider_id
INNER JOIN flight f
    ON f.aircraft_id = a.aircraft_id
ORDER BY me.scheduled_date DESC;