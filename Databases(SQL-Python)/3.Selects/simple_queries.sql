/* Select  date, Reviewer_name, comments and neighbourhood_cleansed where date is less than 2014/05/25
	Output: 3801 rows
*/
select Reviews.date, Reviews.reviewer_name, Reviews.comments, Listings.neighbourhood_cleansed
from Reviews join Listings
on Reviews.listing_id = Listings.id
where date < '20140525';

/* Get all the listings that are available(to book) ordered by their price. Select description, neighbourhood_cleansed, price, transit 
	Output: 1074 rows
*/
select distinct Listings.description, Listings.neighbourhood_cleansed, Listings.price, Listings.transit
from Listings join Calendar on Listings.id = Calendar.listing_id
where Listings.city = 'Athens' AND Calendar.available = 'true'
order by Listings.price;

/* Get all the listings that have been reviewed even the one that we dont have on the Listngs Table
	Output: 416036 rows
 */
select distinct Listings.name, Listings.city, Listings.last_review,Listings.neighbourhood_cleansed, Reviews.date
from Listings full outer join Reviews
on Listings.id = Reviews.listing_id;

/* Get listings name, neighbourhood_cleansedm price, amenities, review_scores_rating from listings that has score greater than 80 and price less than 100$
	Output: 7609 rows
*/
select distinct Listings.name, Listings.neighbourhood_cleansed, Listings.price, Listings.amenities, Listings.review_scores_rating
from Listings join Reviews on Listings.id = Reviews.listing_id
where cast(Listings.review_scores_rating as int) > 80 and length(Listings.price) < 7
order by Listings.price;

/* Select top 20 neighbourhoods with the most listings
	Output: 20 rows
 */
select Neighbourhoods.neighbourhood, count(Listings.neighbourhood_cleansed) as Total_Listings 
from Neighbourhoods join Listings on Neighbourhoods.neighbourhood = Listings.neighbourhood_cleansed
group by Neighbourhoods.neighbourhood
order by count(Listings.neighbourhood_cleansed) desc
limit 20;


/* select the listings that have at least TV on the apparement, price is less than 100$, has at least 4 beds(family), is available(to book), an has at least score more than 75 
	, then order the query by price and finally return as output name, description, amenities, beds, price, review_scores_rating, neighbourhood_cleansed
	Output: 758 rows
*/
select distinct Listings.name, Listings.description, Listings.amenities, Listings.beds, Listings.price, Listings.review_scores_rating, Listings.neighbourhood_cleansed 
from Listings join Calendar on Listings.id = Calendar.listing_id
join Neighbourhoods on Listings.neighbourhood_cleansed = Neighbourhoods.neighbourhood
where Listings.amenities like '%TV%' and length(Listings.price) < 7 and Listings.beds > 3 and Calendar.available = 'true' and cast(Listings.review_scores_rating as int) > 75
order by Listings.price;


/*
	Select date, price, adjusted_price, host_name, neighbourhood that has minimum_nights more than 5 and has at least 4 bedrooms(more than 3), are available to book, thei cost is less than 100$
	and has a score between 90 and 100
	Output: 275 rows
*/

select distinct c.date, c.price, c.adjusted_price, l.host_name, l.neighbourhood
from Calendar c join Listings l
on c.listing_id = l.id
where c.minimum_nights > 5 and l.bedrooms > 3 and c.available = 'true' and length(c.price) < 7
and cast(l.review_scores_rating as int) between 90 and 100;

/*select the Neighbourhoods which have geometry_coordinates_0_0_0_0 between 23.72 and 23.74 
Output: 16 rows */
select distinct Neighbourhoods.neighbourhood from Neighbourhoods join geolocation on Neighbourhoods.neighbourhood = geolocation.properties_neighbourhood where geometry_coordinates_0_0_0_0 between 23.72 and 23.74


/*
	Select name, host_name, price, number_of_reviews, last_review and date of listings that have been summarized and may have summarized their reviews. Get those listings that have price more than 100
	and minimum_nights that someone can stay equals to 5. 
	Output: 6 rows
*/
select ls.name, ls.host_name, ls.price, ls.number_of_reviews, ls.last_review, rs.date
from Listings_summary ls full outer join Reviews_summary rs
on ls.id = rs.listing_id
where ls.price > 100 and ls.minimum_nights = 5;

/*
	Get the summary_listings's name, host_name, neighbourhood, minimum_nights that their price is less than the average price of all listings and neighbourhood starts with ΑΜΠ
	Output: 309 rows
*/


select name, host_name, neighbourhood, minimum_nights
from Listings_Summary
where price < (select avg(price) from listings_summary) 
and neighbourhood like 'ΑΜΠ%'

/*
	select name, transit, house_rules, neighbourhood, price from listings that have been reviewed and have on average more than 0.5 reviews and was reviewed on 2020/01/01
	Output: 391 rows
*/

select l.name, l.transit, l.house_rules, l.neighbourhood, l.price
from Listings l join Reviews r
on l.id = r.listing_id
where cast(l.reviews_per_month as float) > 0.5 and r.date = '20200101'


/*
	Select name, transit, neighbourhood, date, price of the cheapes listings that are available to book and has date more than 2020/01/10
	Output: 327 rows
*/


select distinct l.name, l.transit, l.neighbourhood, c.date, l.price
from Listings l join calendar c
on l.id = c.listing_id
where l.price = (select min(price) from listings)
and c.available = 'true'
and c.date > '20200110'

