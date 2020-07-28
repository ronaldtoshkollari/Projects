create table Reviews(
	listing_id int,
	id int,
	date date,
	reviewer_id int,
	reviewer_name text,
	comments text,
	primary key(id),
	foreign key(listing_id) references Listings(id)
);