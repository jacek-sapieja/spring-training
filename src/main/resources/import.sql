create database bank;
use bank;
create table accounts(id int auto_increment, number varchar(26), balance bigint, primary key(id));

insert into customer values (1, 'jan', '123');
insert role values(1, 'ROLE_ADMIN');
insert into customer_roles values(1,1);