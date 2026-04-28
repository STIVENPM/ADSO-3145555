DO $$
DECLARE
    v_person_id uuid;
    v_document_type_id uuid;
BEGIN
    SELECT p.person_id
    INTO v_person_id
    FROM person p
    ORDER BY p.created_at
    LIMIT 1;

    SELECT dt.document_type_id
    INTO v_document_type_id
    FROM document_type dt
    ORDER BY dt.created_at
    LIMIT 1;

    IF v_person_id IS NULL THEN
        RAISE EXCEPTION 'No existe person disponible.';
    END IF;

    CALL sp_register_person_document(
        v_person_id,
        v_document_type_id,
        'DOC123456'
    );
END;
$$;