create database bank;
use bank;
create table accounts(id int auto_increment, number varchar(26), balance bigint, primary key(id));
create table history(id int auto_increment, number varchar(26), funds bigint, type varchar(30), timestamp timestamp, primary key(id));