DO $$
DECLARE
    v_aircraft_id uuid;
    v_maintenance_type_id uuid;
    v_provider_id uuid;
BEGIN
    SELECT a.aircraft_id
    INTO v_aircraft_id
    FROM aircraft a
    ORDER BY a.created_at
    LIMIT 1;

    SELECT mt.maintenance_type_id
    INTO v_maintenance_type_id
    FROM maintenance_type mt
    ORDER BY mt.created_at
    LIMIT 1;

    SELECT mp.maintenance_provider_id
    INTO v_provider_id
    FROM maintenance_provider mp
    ORDER BY mp.created_at
    LIMIT 1;

    IF v_aircraft_id IS NULL THEN
        RAISE EXCEPTION 'No existe aircraft disponible.';
    END IF;

    CALL sp_schedule_maintenance(
        v_aircraft_id,
        v_maintenance_type_id,
        v_provider_id,
        now() + interval '1 day'
    );
END;
$$;