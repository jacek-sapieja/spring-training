create database bank;
use bank;
create table accounts(id int auto_increment, number varchar(26), balance bigint, primary key(id));

create table users(username varchar(45), password varchar(45), enabled tinyint(1), primary key(username));
create table authorities(username varchar(45), authority varchar(45), primary key(username));

insert into users values ('jan', '202cb962ac59075b964b07152d234b70', 1);
insert into authorities values ('jan', 'ROLE_ADMIN');