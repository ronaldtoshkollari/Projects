/*
	Getting name ,space, transit, price from listings that price is between 
	100 and 150 and are located at Plaka.
	168 Rows Affected 
*/


select l.name, l.space, l.transit, p.price
from listing l join room r
on l.id = r.listing_id
join price p
on l.id = p.listing_id
join location loc
on l.id = loc.listing_id
where p.price > 100 and p.price < 150 and loc.neighbourhood = 'Plaka';

/*
	Getting name, summary, description, neighborhood_overview, transit, amenity_name from listings
	that has a TV on their amenities.
	4 Rows Affected 

*/
select l.name, l.summary, l.description, l.neighborhood_overview, l.transit, a.amenity_name
from listing l join room r
on l.id = r.listing_id
join Room_Amenity ra
on ra.room_id = l.id
join Amenity a
on ra.amenity_id = a.amenity_id
where a.amenity_name = 'TV';

/*
	Getting the description an total count of amenities from listings
	joining them with room even if they don't exist on the table(full outer join)
	and taking the listings that have more than 40 total amenities.
	818 Rows affected

*/

select l.description, count(a.amenity_name) as Total_Amenities
from listing l
full outer join room r
on l.id = r.listing_id
join Room_Amenity ra
on ra.room_id = r.id
join Amenity a
on a.amenity_id = ra.amenity_id
group by l.description
having  count(a.amenity_name) > 40;

/* 
	Getting name of host and their total listings (hosts are selected even if they do not have any listing (outer join))
	and showing hosts that has more than 3 listings.
	117 Rows affected

*/

select h.name, count(l.id) as Total_Listings
from host h
full outer join listing l
on h.id = l.host_id
join location loc
on l.id = loc.listing_id
where loc.neighbourhood = 'Plaka'
group by h.name
having count(l.id) > 3;


/*
	For the next query we should add the following foreign key reference
	
*/

alter table geolocation
add constraint neighbourhood_fk
foreign key(properties_neighbourhood) references neighbourhood(neighbourhood);


/*
	Checking if our neighbourhoods are in our geolocation table, and taking the one with
	cordinates0000 = 23.724316 and cordinates0001 = 37.975593
	1 Row affected
*/

select n.neighbourhood, count(g.properties_neighbourhood) 
from geolocation g
join neighbourhood n
on g.properties_neighbourhood = n.neighbourhood
where geometry_coordinates_0_0_0_0 = 23.724316 and geometry_coordinates_0_0_0_1 = 37.975593
group by n.neighbourhood;

