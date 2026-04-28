DO $$
DECLARE
    v_ticket_segment_id uuid;
    v_aircraft_seat_id uuid;
BEGIN
    SELECT ts.ticket_segment_id
    INTO v_ticket_segment_id
    FROM ticket_segment ts
    LEFT JOIN seat_assignment sa
        ON sa.ticket_segment_id = ts.ticket_segment_id
    WHERE sa.seat_assignment_id IS NULL
    ORDER BY ts.created_at
    LIMIT 1;

    SELECT acs.aircraft_seat_id
    INTO v_aircraft_seat_id
    FROM aircraft_seat acs
    ORDER BY acs.created_at
    LIMIT 1;

    IF v_ticket_segment_id IS NULL THEN
        RAISE EXCEPTION 'No existe ticket_segment sin asiento asignado.';
    END IF;

    CALL sp_assign_seat(
        v_ticket_segment_id,
        v_aircraft_seat_id
    );
END;
$$;