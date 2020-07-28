--LOCATION TABLE--

create table Location(
	listing_id int,
	street varchar(70),
	neighbourhood varchar(20),
	neighbourhood_cleansed varchar(40),
	city varchar(40),
	state varchar(40),
	zipcode varchar(20),
	market varchar(30),
	smart_location varchar(40),
	country_code varchar(10),
    country varchar(10),
    latitude varchar(10),
    longitude varchar(10),
    is_location_exact boolean,
	foreign key (listing_id) references Listings(id)
);



insert into Location
select id, street, neighbourhood, neighbourhood_cleansed, city, state, zipcode, zipcode,
		smart_location, country_code, country, latitude, longitude, is_location_exact
from listings;


alter table Listings
drop constraint listings_neighbourhood_cleansed_fkey;


alter table Location
add foreign key (neighbourhood_cleansed) references Neighbourhoods(neighbourhood);

alter table listings
drop column street,
drop column neighbourhood,
drop column neighbourhood_cleansed,
drop column city,
drop column state,
drop column zipcode,
drop column market,
drop column smart_location,
drop column country_code,
drop column country,
drop column latitude,
drop column longitude,
drop column is_location_exact;
