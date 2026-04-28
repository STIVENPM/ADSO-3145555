DROP TRIGGER IF EXISTS trg_ai_person_document_validate ON person_document;
DROP FUNCTION IF EXISTS fn_ai_person_document_validate();
DROP PROCEDURE IF EXISTS sp_register_person_document(uuid, uuid, varchar);

CREATE OR REPLACE FUNCTION fn_ai_person_document_validate()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
    -- Validate document uniqueness
    IF EXISTS (
        SELECT 1
        FROM person_document pd
        WHERE pd.document_number = NEW.document_number
        AND pd.document_type_id = NEW.document_type_id
        AND pd.person_id != NEW.person_id
    ) THEN
        RAISE EXCEPTION 'Document already exists for another person';
    END IF;

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_ai_person_document_validate
AFTER INSERT ON person_document
FOR EACH ROW
EXECUTE FUNCTION fn_ai_person_document_validate();

CREATE OR REPLACE PROCEDURE sp_register_person_document(
    p_person_id uuid,
    p_document_type_id uuid,
    p_document_number varchar(50)
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO person_document (
        person_id,
        document_type_id,
        document_number,
        issued_at
    )
    VALUES (
        p_person_id,
        p_document_type_id,
        p_document_number,
        now()
    );
END;
$$;

-- Consulta resuelta: identidad de pasajeros y documentos
SELECT
    p.first_name,
    p.last_name,
    pd.document_number,
    dt.type_name,
    pc.contact_value,
    ct.type_name,
    rp.sequence_number
FROM person p
INNER JOIN person_document pd
    ON pd.person_id = p.person_id
INNER JOIN person_contact pc
    ON pc.person_id = p.person_id
INNER JOIN document_type dt
    ON dt.document_type_id = pd.document_type_id
INNER JOIN contact_type ct
    ON ct.contact_type_id = pc.contact_type_id
INNER JOIN reservation_passenger rp
    ON rp.person_id = p.person_id
ORDER BY p.created_at DESC;