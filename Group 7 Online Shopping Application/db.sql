-- Build fresh schema with seed data
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS order_products;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS admin;
SET FOREIGN_KEY_CHECKS = 1;

CREATE DATABASE IF NOT EXISTS onlineshop;
USE onlineshop;

CREATE TABLE admin (
    admin_id INT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE product (
    product_id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL,
    stock_quantity INT NOT NULL
);

CREATE TABLE customer (
    customer_id INT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL
);

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    CONSTRAINT fk_orders_customer FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE order_products (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT fk_op_order FOREIGN KEY (order_id) REFERENCES orders(order_id)
        ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT fk_op_product FOREIGN KEY (product_id) REFERENCES product(product_id)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

-- Seed admin
INSERT INTO admin VALUES (1, 'sakshi', '1234', 'sakshi@example.com');

-- Seed products
INSERT INTO product VALUES
(101, 'T-Shirt', 560.0, 100),
(102, 'Trouser', 1400.0, 50),
(103, 'Shoes', 2200.0, 35);

-- Seed customers
INSERT INTO customer VALUES
(1001, 'Aniket', 'aniket@gmail.com', 'Nashik'),
(1002, 'Pooja', 'pooja@gmail.com', 'Pune');

-- Seed one order for Aniket: 2x T-Shirt, 1x Shoes
INSERT INTO orders (customer_id, status) VALUES (1001, 'CREATED');
SET @oid = LAST_INSERT_ID();
INSERT INTO order_products (order_id, product_id, quantity) VALUES
(@oid, 101, 2),
(@oid, 103, 1);
UPDATE product SET stock_quantity = stock_quantity - 2 WHERE product_id = 101;
UPDATE product SET stock_quantity = stock_quantity - 1 WHERE product_id = 103;
