DROP TABLE IF EXISTS PRODUCT;
CREATE TABLE product(
product_id BIGINT NOT NULL auto_increment PRIMARY KEY,
name VARCHAR(30),
description VARCHAR(30),
price INT
);