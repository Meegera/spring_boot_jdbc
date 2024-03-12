create table CUSTOMERS(
  id int AUTO_INCREMENT primary key ,
  name varchar(30),
  surname varchar(50),
  age int check (age >= 0),
  phone_number varchar(20)
);

create table ORDERS(
   id int AUTO_INCREMENT primary key ,
   date date,
   customer_id int,
   product_name varchar(100),
   amount int,
   foreign key (customer_id) REFERENCES CUSTOMERS(id)
);