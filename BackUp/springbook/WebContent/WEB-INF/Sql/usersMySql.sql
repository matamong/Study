create user me@localhost identified by ‘tiger’ 

create database me; 

grant all privileges on me.* to me@localhost; 

flush privileges; 

 

create database me;

use me;

create table users(
	Id varchar(10) primary key,
	Name varchar(20) not null,
	Password varchar(20) not null,
	Level tinyint not null,
	Login int not null,
	Recommend int not null,
    Email varchar(50) not null
);