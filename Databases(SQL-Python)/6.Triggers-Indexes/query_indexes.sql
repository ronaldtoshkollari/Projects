-- 1st Query --
/* Query 1: w/out index: 55.085 ms; w/index: 27.353 ms, we used index on host_id of listings_copy(copy of listings that you asked to make) because the amount of rows in listings*/
create index host_id_index
on Listings_copy(host_id);

-- 2nd Query --
/* Query 2: w/out index: 1986.140 ms; w/index: 1921.158 ms, we used index on price table on filter attributes*/
create index filter_index
on Price(guests_included, price);

-- 3rd Query --
/* Query 3: w/out index: 216.032 ms; w/index: 15.074 ms, we used index on listings_copy id because query plan shows us that it is taking too much time on joining tables*/
create index listings_index
on listings_copy(id);

-- 4th Query --
/* Query 4: w/out index: 714.641 ms; w/index: 19.348 ms, we used index on listings_id of table Room as we can see the plan shows us that query is taking too much time on table join of Room and listings*/
create index room_listing_id_index
on Room(listing_id);

-- 5th Query --
/* Query 5: w/out index: 3599.154 ms; w/index: 3471.348 ms, we used index on description as we can see improvment is not that good, but as we can see plan shows us that is take too much time on group by*/
create index description_index
on listings_copy(description);

-- 6th Query --
/* Query 6: w/out index: 21.074 ms; w/index: 20.965 ms, we used index on filter improvment is not the best because query is already improved with the indexes that we created earlier an it has come to its limit*/
create index neighbourhood
on location(neighbourhood);

-- 7th Query --
/* Query 7: w/out index: 1.088 ms; w/index: 0.123 ms, we used index on filter, because the previous index helps and the only way to improve the plan is creating an index on its filter*/
create index properties_neighbourhood_index
on geolocation(properties_neighbourhood);
