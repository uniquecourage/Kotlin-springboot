create table if not exists students (
id serial not null,
name varchar(16) not null,
number varchar(32) not null,
city varchar(64),
register_date date,
primary key (id)
);

create table if not exists scores (
id serial not null,
number varchar(32) not null,
subject varchar(64) not null,
score int not null,
primary key (id)
);