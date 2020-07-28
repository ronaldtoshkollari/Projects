--HOST TABLE--
create table Host(
	id int,
	url text,
	name varchar(40),
	since date,
	location varchar(100),
	about text,
	response_time varchar(20),
	response_rate varchar(10),
	acceptance_rate varchar(10),
	is_superhost boolean,
	thumbnail_url varchar(110),
	picture_url varchar(110),
	neighbourhood varchar(30),
	listings_count int,
	total_listings_count int,
	verifications varchar(150),
	has_profile_pic boolean,
	identity_verified boolean,
	primary key(id)
);

insert into host
select distinct host_id, host_url, host_name, host_since, host_location, host_about, host_response_time,
		host_response_rate, host_acceptance_rate, host_is_superhost, host_thumbnail_url, 
		host_picture_url, host_neighbourhood, host_listings_count, host_total_listings_count,
		host_verifications, host_has_profile_pic, host_identity_verified
from listings;

alter table listings
add foreign key(host_id) references host(id);

--DROPS ARE DONE LAST--
alter table listings
drop column host_url,
drop column host_name,
drop column host_since, 
drop column host_location,
drop column host_about,
drop column host_response_time,
drop column host_response_rate, 
drop column host_acceptance_rate,
drop column host_is_superhost,
drop column host_thumbnail_url,
drop column host_picture_url, 
drop column host_neighbourhood,
drop column host_listings_count,
drop column host_total_listings_count,
drop column host_verifications, 
drop column host_has_profile_pic,
drop column host_identity_verified;