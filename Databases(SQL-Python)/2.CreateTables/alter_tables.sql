ALTER TABLE Listings
ALTER COLUMN license TYPE text;

ALTER TABLE Listings
ALTER COLUMN neighbourhood_cleansed TYPE varchar(40);

ALTER TABLE Calendar
ALTER COLUMN price TYPE varchar(20);

ALTER TABLE Calendar
ALTER COLUMN adjusted_price TYPE varchar(20);