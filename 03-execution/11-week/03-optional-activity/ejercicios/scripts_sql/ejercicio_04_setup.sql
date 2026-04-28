DROP TRIGGER IF EXISTS trg_ai_miles_transaction_update_tier ON miles_transaction;
DROP FUNCTION IF EXISTS fn_ai_miles_transaction_update_tier();
DROP PROCEDURE IF EXISTS sp_accumulate_miles(uuid, numeric, uuid);

CREATE OR REPLACE FUNCTION fn_ai_miles_transaction_update_tier()
RETURNS trigger
LANGUAGE plpgsql
AS $$
DECLARE
    v_total_miles numeric;
    v_new_tier_id uuid;
BEGIN
    -- Calculate total miles for the account
    SELECT SUM(mt.miles_amount)
    INTO v_total_miles
    FROM miles_transaction mt
    WHERE mt.loyalty_account_id = NEW.loyalty_account_id;

    -- Determine new tier based on total miles (simplified)
    SELECT lt.loyalty_tier_id
    INTO v_new_tier_id
    FROM loyalty_tier lt
    WHERE lt.min_miles <= v_total_miles
    ORDER BY lt.min_miles DESC
    LIMIT 1;

    IF v_new_tier_id IS NOT NULL THEN
        -- Update or insert tier
        INSERT INTO loyalty_account_tier (loyalty_account_id, loyalty_tier_id, tier_start_date)
        VALUES (NEW.loyalty_account_id, v_new_tier_id, now())
        ON CONFLICT (loyalty_account_id) DO UPDATE SET
            loyalty_tier_id = EXCLUDED.loyalty_tier_id,
            tier_start_date = now();
    END IF;

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_ai_miles_transaction_update_tier
AFTER INSERT ON miles_transaction
FOR EACH ROW
EXECUTE FUNCTION fn_ai_miles_transaction_update_tier();

CREATE OR REPLACE PROCEDURE sp_accumulate_miles(
    p_loyalty_account_id uuid,
    p_miles_amount numeric,
    p_ticket_segment_id uuid
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO miles_transaction (
        loyalty_account_id,
        transaction_type,
        miles_amount,
        ticket_segment_id,
        transaction_date
    )
    VALUES (
        p_loyalty_account_id,
        'earn',
        p_miles_amount,
        p_ticket_segment_id,
        now()
    );
END;
$$;

-- Consulta resuelta: acumulación de millas y niveles de fidelización
SELECT
    la.account_number,
    mt.transaction_type,
    mt.miles_amount,
    mt.transaction_date,
    lat.tier_start_date,
    lt.tier_name,
    lt.min_miles,
    ts.ticket_segment_id,
    fs.segment_number
FROM loyalty_account la
INNER JOIN miles_transaction mt
    ON mt.loyalty_account_id = la.loyalty_account_id
INNER JOIN loyalty_account_tier lat
    ON lat.loyalty_account_id = la.loyalty_account_id
INNER JOIN loyalty_tier lt
    ON lt.loyalty_tier_id = lat.loyalty_tier_id
INNER JOIN ticket_segment ts
    ON ts.ticket_segment_id = mt.ticket_segment_id
INNER JOIN flight_segment fs
    ON fs.flight_segment_id = ts.flight_segment_id
ORDER BY mt.transaction_date DESC;