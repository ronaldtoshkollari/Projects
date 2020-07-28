--Creation of demsnion tables

create table Locations(
	location_name varchar(150) primary key
);

create table Sex(
	sex_name varchar(150) primary key
);

create table Material(
	material_name varchar(150) primary key
);

create table Departments(
	department_code varchar(150) primary key
);

create table LoanFact(
	location_name varchar(150),
	sex_name varchar(150),
	material_name varchar(150),
	department_code varchar(150),
	time_key datetime
	foreign key (material_name) references Material(material_name),
	foreign key (sex_name) references Sex(sex_name),
	foreign key (department_code) references Departments(department_code),
	foreign key (location_name) references Locations(location_name),
	foreign key (time_key) references timeinfo(time_key)
);

create table timeinfo
(time_key datetime primary key,
 t_year int,
 t_month int,
 t_dayofmonth int
); 

--Adding values into demsnion tables and fact table
insert into Sex 
select distinct sex 
from LIBRARY.dbo.borrowers;

insert into Locations 
select distinct copyloc 
from LIBRARY.dbo.copies;

insert into Material 
select distinct material 
from LIBRARY.dbo.bibrecs;

insert into Departments 
select distinct depcode 
from LIBRARY.dbo.departments;

insert into timeinfo
select distinct loandate, datepart(year, loandate), datepart(month, loandate), datepart(day, loandate)
from LIBRARY.dbo.loanstats;

insert into LoanFact
select distinct cop.copyloc, bor.sex, bib.material, dep.depcode, loa.loandate
from LIBRARY.dbo.copies as cop
join LIBRARY.dbo.loanstats as loa
on cop.copyno = loa.copyno
join LIBRARY.dbo.bibrecs as bib
on cop.bibno = bib.bibno
join LIBRARY.dbo.borrowers as bor
on bor.bid = loa.bid
join LIBRARY.dbo.departments as dep
on dep.depcode = bor.depcode;
