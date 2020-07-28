--PRICE TABLE--
create table Price(
	listing_id int,
	price float,
	weekly_price float,
	monthly_price float,
	security_deposit float,
	cleaning_fee float,
	guests_included int,
	extra_people float,
	minimum_nights int, 
	maximum_nights int,
	minimum_minimum_nights int,
	maximum_minimum_nights int, 
	minimum_maximum_nights int, 
	maximum_maximum_nights int,
	minimum_nights_avg_ntm float,
	maximum_nights_avg_ntm float,
	foreign key(listing_id) references Listings(id)
);


insert into price
select id, cast(replace(split_part(replace(price, ',', '.'), '.', 1), '$', '') as float), cast(replace(split_part(replace(weekly_price, ',', '.'), '.', 1), '$', '') as float), 
		cast(replace(split_part(replace(monthly_price, ',', '.'), '.', 1), '$', '') as float), 
		cast(replace(split_part(replace(security_deposit, ',', '.'), '.', 1), '$', '') as float), cast(replace(cleaning_fee, '$', '') as float), 
		guests_included, cast(replace(extra_people, '$', '') as float), minimum_nights, maximum_nights,
		minimum_minimum_nights, maximum_minimum_nights, minimum_maximum_nights, maximum_maximum_nights, cast(minimum_nights_avg_ntm as float),
		cast (maximum_nights_avg_ntm as float)
from listings;

alter table Listings
drop column cleaning_fee,
drop column guests_included,
drop column extra_people,
drop column minimum_nights,
drop column maximum_nights,
drop column minimum_minimum_nights,
drop column maximum_minimum_nights,
drop column minimum_maximum_nights,
drop column maximum_maximum_nights,
drop column minimum_nights_avg_ntm,
drop column maximum_nights_avg_ntm;