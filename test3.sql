create database test3_pizza;
use test3_pizza;

create table orders(
orderId int primary key auto_increment,
name varchar(50) not null,
contact varchar(15) not null,
size enum("s","m","l","xl") not null,
toppings int not null,
total double not null

);
describe orders;
insert into orders
values
(1,"demo","123455","s",3,"100");
select * from orders;