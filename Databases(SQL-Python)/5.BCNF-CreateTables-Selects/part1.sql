--Create Amenity table--
create table Amenity(
	
	amenity_id bigserial,
	amenity_name text,
	primary key(amenity_id)

);


--Removing chars that we dont need
update Room
set amenities = replace(amenities, '{', '');

update Room
set amenities = replace(amenities, '}', '');

update Room
set amenities = replace(amenities, '"', '');

--Adding each ammenity to out amanity table--
insert into amenity(amenity_name)
select distinct regexp_split_to_table(cast(amenities as text),',') from room where amenities != '' and amenities notnull;

--Adding Primary key id to room table
alter table room
add column id bigserial primary key;

--This table is used to connect room table and amanity table, it represents a duo that is refered to room,amanity.
create table Room_Amenity(
	amenity_id bigint,
	room_id bigint,
	primary key(amenity_id, room_id),
	foreign key(amenity_id) references Amenity(amenity_id),
	foreign key(room_id) references Room(id)
);


--Subquery is returning duos of Room.id and Amenity.amenity_name and for each duo we add the proper duo of Room.id,Amenity.amenity_id to our new talbe
insert into Room_Amenity(room_id,amenity_id)
select Room.id,Amenity.amenity_id
from Amenity, Room
where (Room.id,Amenity.amenity_name) in (
select Room.id,regexp_split_to_table(cast(Room.amenities as text),',')
from Room
where amenities != '' and amenities notnull);


--Finally drop column of amenities on table Room. Rooms are now connected to amenities with Room_Amenity table
alter table Room
Drop Column amenities;