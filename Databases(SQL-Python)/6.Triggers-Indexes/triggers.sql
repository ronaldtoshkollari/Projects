CREATE OR REPLACE FUNCTION update_number_of_host_listings_count() RETURNS TRIGGER AS
$BODY$
BEGIN
	IF (TG_OP = 'DELETE') THEN
		UPDATE Host SET listings_count = listings_count - 1 WHERE id = OLD.host_id;
		UPDATE Host SET total_listings_count = total_listings_count - 1 WHERE id = OLD.host_id;
		RETURN OLD;
	ELSEIF (TG_OP = 'INSERT') THEN
		UPDATE Host SET listings_count = listings_count + 1 WHERE id = NEW.host_id;
		UPDATE Host SET total_listings_count = total_listings_count + 1 WHERE id = NEW.host_id;
		RETURN NEW;
	END IF;
END;
$BODY$ LANGUAGE plpgsql;



CREATE TRIGGER total_listings_update
AFTER INSERT OR DELETE ON Listing
	FOR EACH ROW EXECUTE PROCEDURE update_number_of_host_listings_count();



-- Here we have total_listings = 1
select * 
from listing join host
on host.id = listing.host_id
where host.id = 706768;


--Now i am going to delete a listing row
delete from Listing_Summary where id = 6383348;
delete from Review_Summary where listing_id = 6383348;
delete from review where listing_id = 6383348;
delete from calendar where listing_id = 6383348;
delete from Room_amenity where room_id = 1317;
delete from Room where listing_id = 6383348;
delete from price where listing_id = 6383348;
delete from location where listing_id = 6383348;
delete from listing where id = 6383348;

-- Now if we search the following query we will see that the wanted fields has been updated to 0
select * from Host where id = 706768;

-- Now we are going to add one listing on the table 
insert into Listing(id, host_id) values(6383348, 706768);

-- Check that the listing is inserted 
select * 
from listing join host
on host.id = listing.host_id
where host.id = 706768;

-- Now if we get the host we will see than after insert ou r wanted field has been updated to 1
select * from Host where id = 706768;



-- Our Trigger -- 
CREATE OR REPLACE FUNCTION numerical_checks() RETURNS TRIGGER AS
$BODY$
BEGIN

	IF NEW.price < 0 THEN
		RAISE EXCEPTION 'price of % cannot negative', NEW.id;
	END IF;
	
	IF NEW.minimum_nights < 0 THEN
		RAISE EXCEPTION 'minimum_nights of % cannot negative', NEW.id;
	END IF;
	
	IF NEW.number_of_reviews < 0 THEN
		RAISE EXCEPTION 'number_of_reviews of % cannot negative', NEW.id;
	END IF;
	
	IF NEW.calculated_host_listings_count < 0 THEN
		RAISE EXCEPTION 'calculated_host_listings_count of % cannot negative', NEW.id;
	END IF;
	
	IF NEW.availability_365 > 365 THEN
		RAISE EXCEPTION 'availability_365 of % cannot be more than 365', NEW.id;
	END IF;
	
	RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

-- Create TRIGGER
CREATE TRIGGER numerical_check_table BEFORE INSERT OR UPDATE ON listing_summary
	FOR EACH ROW EXECUTE PROCEDURE numerical_checks();
	
	
-- Checking TRIGGERS
insert into listing_summary(id, host_id, neighbourhood, price) values(1, 37177, 'ΑΜΠΕΛΟΚΗΠΟΙ', -20);

-- Result
ERROR:  price of 1 cannot negative
CONTEXT:  PL/pgSQL function numerical_checks() line 5 at RAISE
SQL state: P0001

-- Checking TRIGGERS
insert into listing_summary(id, host_id, neighbourhood, minimum_nights) values(1, 37177, 'ΑΜΠΕΛΟΚΗΠΟΙ', -20);

-- Result
ERROR:  minimum_nights of 1 cannot negative
CONTEXT:  PL/pgSQL function numerical_checks() line 9 at RAISE
SQL state: P0001

-- Checking TRIGGERS
insert into listing_summary(id, host_id, neighbourhood, number_of_reviews) values(1, 37177, 'ΑΜΠΕΛΟΚΗΠΟΙ', -20);

-- Result
ERROR:  number_of_reviews of 1 cannot negative
CONTEXT:  PL/pgSQL function numerical_checks() line 13 at RAISE
SQL state: P0001

-- Checking TRIGGERS
insert into listing_summary(id, host_id, neighbourhood, calculated_host_listings_count) values(1, 37177, 'ΑΜΠΕΛΟΚΗΠΟΙ', -20);

-- Result
ERROR:  calculated_host_listings_count of 1 cannot negative
CONTEXT:  PL/pgSQL function numerical_checks() line 17 at RAISE
SQL state: P0001

-- Checking TRIGGERS
insert into listing_summary(id, host_id, neighbourhood, availability_365) values(1, 37177, 'ΑΜΠΕΛΟΚΗΠΟΙ', -20);

-- Result
ERROR:  availability_365 of 1 cannot be more than 365
CONTEXT:  PL/pgSQL function numerical_checks() line 21 at RAISE
SQL state: P0001




