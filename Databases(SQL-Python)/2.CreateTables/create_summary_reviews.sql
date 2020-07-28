create table Reviews_Summary(
	listing_id int,
	date date,
	foreign key(listing_id) references Listings(id)
);