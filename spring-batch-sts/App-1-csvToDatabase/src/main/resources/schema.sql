drop table if exists product;

create table product(
id 	INT NOT NULL  primary key AUTO_INCREMENT,
name VARCHAR(40),
email VARCHAR(50),
salary INT
);