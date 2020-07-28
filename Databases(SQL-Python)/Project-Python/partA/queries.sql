--Create tables queries
create table Keywords(
	id bigint primary key,
	keywords json
);

create table credits(
	cast_json json,
	crew json,
	id int primary key
);

CREATE TABLE Links(
	movieId bigint,
	imdbId bigint,
	tmdbId bigint,
	primary key(movieId, imdbId, tmdbId)
);

create table movies_metadata(
	adult boolean,
	belongs_to_collention json,
	budget int,
	genres json,
	homepage text,
	id bigint primary key,
	imdb_id int,
	original_language varchar(20),
	original_title text,
	overview text,
	popularity float,
	poster_path text,
	production_companies json,
	production_countries json,
	release_date date,
	revenue bigint,
	runtime float,
	spoken_languages json,
	status varchar(50),
	tagline text,
	title text,
	video boolean,
	vote_average float,
	vote_count int
);

create table Ratings(
	userId bigint,
	movieId bigint,
	rating float,
	timestamp varchar(100),
	primary key(userId, movieId)
);


-- Queries to handle data on tables
DELETE FROM keywords
WHERE keywords.id NOT IN(select id from movies_metadata);

ALTER TABLE keywords
ADD FOREIGN KEY (id) REFERENCES movies_metadata(id);

DELETE FROM credits
WHERE credits.id NOT IN(select id from movies_metadata);

ALTER TABLE credits
ADD FOREIGN KEY (id) REFERENCES movies_metadata(id);

DELETE FROM links
WHERE links.tmdbid NOT IN(select id from movies_metadata);

ALTER TABLE links
ADD FOREIGN KEY (tmdbid) REFERENCES movies_metadata(id);

DELETE FROM ratings
WHERE ratings.movieid NOT IN(select id from movies_metadata);

ALTER TABLE ratings
ADD FOREIGN KEY (movieid) REFERENCES movies_metadata(id);



-- The following commads were used to import csv data into our tables.

--\copy Keywords FROM 'C:\Users\Roni\Desktop\Earino_2020\Baseis Dedomenwn\Ergasies\Project\keywords_wo_duplicates.csv' DELIMITER ',' CSV HEADER;
--\copy Credits FROM 'C:\Users\Roni\Desktop\Earino_2020\Baseis Dedomenwn\Ergasies\Project\credits_wo_duplicates.csv' DELIMITER ',' CSV HEADER;
--\copy Links FROM 'C:\Users\Roni\Desktop\Earino_2020\Baseis Dedomenwn\Ergasies\Project\links_wo_duplicates.csv' DELIMITER ',' CSV HEADER;
--\copy movies_metadata FROM 'C:\Users\Roni\Desktop\Earino_2020\Baseis Dedomenwn\Ergasies\Project\movies_metadata_wo_duplicates.csv' DELIMITER ',' CSV HEADER;
--\copy Ratings FROM 'C:\Users\Roni\Desktop\Earino_2020\Baseis Dedomenwn\Ergasies\Project\ratings.csv' DELIMITER ',' CSV HEADER;


