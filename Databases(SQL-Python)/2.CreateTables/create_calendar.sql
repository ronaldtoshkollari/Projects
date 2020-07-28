create table Calendar(
	listing_id int,
	date date,
	available boolean,
	price float,
	adjusted_price float,
	minimum_nights int,
	maximum_nights int,
	primary key(listing_id, date),
	foreign key(listing_id) references Listings(id)
);
