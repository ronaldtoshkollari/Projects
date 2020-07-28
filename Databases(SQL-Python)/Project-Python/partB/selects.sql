-- 1st query, 135 rows affected
select count(*) as total_movies, EXTRACT(YEAR FROM release_date) as Year
from movies_metadata
where release_date notnull
group by EXTRACT(YEAR FROM release_date)
order by EXTRACT(YEAR FROM release_date) desc

-- 2nd query, 20 rows affected
select count(*) as total_movies, json_array_elements(genres)->> 'name' as Genre
from movies_metadata
group by json_array_elements(genres)->> 'name'

-- 3rd query Here we have to notice that an movie can belong to multiple genres. 2021 rows affected
select count(*) as total_movies, json_array_elements(genres)->> 'name' as Genre, EXTRACT(YEAR FROM release_date) as Year
from movies_metadata
where release_date notnull
group by json_array_elements(genres)->> 'name', EXTRACT(YEAR FROM release_date)
order by EXTRACT(YEAR FROM release_date) desc;

--4th query, 20 rows affected
select avg(r.rating) as average_rating, json_array_elements(genres)->> 'name' as Genre  from ratings as r
join movies_metadata as mm
on mm.id = r.movieid
group by json_array_elements(genres)->> 'name';

--5th query, 265917 rows affected
select userid, count(*) as total_ratings_of_user
from ratings
group by userid;

--6th query, 265917 rows affected
select userid, avg(rating) as average_rating_of_user
from ratings
group by userid;

--View 
create view user_ratings_statistics as
select userid, count(*) as total_ratings_of_user, avg(rating) as average_rating_of_user
from ratings
group by userid;