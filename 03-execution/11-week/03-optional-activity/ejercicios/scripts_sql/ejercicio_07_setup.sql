DROP TRIGGER IF EXISTS trg_ai_seat_assignment_validate ON seat_assignment;
DROP FUNCTION IF EXISTS fn_ai_seat_assignment_validate();
DROP PROCEDURE IF EXISTS sp_assign_seat(uuid, uuid);

CREATE OR REPLACE FUNCTION fn_ai_seat_assignment_validate()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
    -- Validate no double assignment
    IF EXISTS (
        SELECT 1
        FROM seat_assignment sa
        WHERE sa.aircraft_seat_id = NEW.aircraft_seat_id
        AND sa.ticket_segment_id != NEW.ticket_segment_id
    ) THEN
        RAISE EXCEPTION 'Seat already assigned';
    END IF;

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_ai_seat_assignment_validate
AFTER INSERT ON seat_assignment
FOR EACH ROW
EXECUTE FUNCTION fn_ai_seat_assignment_validate();

CREATE OR REPLACE PROCEDURE sp_assign_seat(
    p_ticket_segment_id uuid,
    p_aircraft_seat_id uuid
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO seat_assignment (
        ticket_segment_id,
        aircraft_seat_id,
        assigned_at
    )
    VALUES (
        p_ticket_segment_id,
        p_aircraft_seat_id,
        now()
    );
END;
$$;

-- Consulta resuelta: asignación de asientos y equipaje
SELECT
    ts.ticket_segment_id,
    sa.seat_number,
    sa.assigned_at,
    b.baggage_tag,
    b.weight_kg,
    acs.seat_class,
    fs.segment_number
FROM ticket_segment ts
INNER JOIN seat_assignment sa
    ON sa.ticket_segment_id = ts.ticket_segment_id
INNER JOIN baggage b
    ON b.ticket_segment_id = ts.ticket_segment_id
INNER JOIN aircraft_seat acs
    ON acs.aircraft_seat_id = sa.aircraft_seat_id
INNER JOIN flight_segment fs
    ON fs.flight_segment_id = ts.flight_segment_id
ORDER BY sa.assigned_at DESC;