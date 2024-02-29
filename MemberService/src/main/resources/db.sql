CREATE OR REPLACE FUNCTION update_member_count()
RETURNS TRIGGER AS $update_member_count$
BEGIN
    -- Update fund
    UPDATE fund
    SET total_members = total_members + 1
    WHERE fund.fund_id = NEW.fund_id;

    RETURN NEW;
END;
$update_member_count$ LANGUAGE plpgsql;

CREATE TRIGGER add_new_member
AFTER INSERT ON member
FOR EACH ROW
EXECUTE FUNCTION update_member_count();


-- Step 1: Drop the existing foreign key constraint
ALTER TABLE "transaction"  DROP CONSTRAINT fk_member_id;

-- Step 2: Recreate the foreign key constraint with deferrable options
ALTER TABLE "transaction"
    ADD CONSTRAINT fk_member_id FOREIGN KEY (member_id)
    REFERENCES member(member_id) ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;

