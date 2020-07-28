--CALENDAR TABLE--
update
	calendar
set 
	price = replace(price, '$', '')

update
	calendar
set 
	adjusted_price = replace(adjusted_price, '$', '');
	
	
update calendar
set price = split_part(price, '.', 1);

update calendar
set price = replace(price, ',', '.');

alter table calendar
alter column price type float USING price::double precision;


update calendar
set adjusted_price = split_part(adjusted_price, '.', 1);

update calendar
set adjusted_price = replace(adjusted_price, ',', '.');

alter table calendar
alter column adjusted_price type float USING adjusted_price::double precision;