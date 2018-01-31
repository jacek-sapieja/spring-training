create database bank;
use bank;
create table accounts(id int auto_increment, number varchar(26), balance bigint, primary key(id));

insert into Customer values (1, 'jan', '', '', 'fd66aa8bbeb27853026132a01068de7108a6a5b6d1a6bf3c423cdd1062ae33762eba7bc71bb90b0b');
insert Role values(1, 'ROLE_ADMIN');
insert into Customer_Role values(1,1);