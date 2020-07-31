BEGIN;

DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('name1'), ('name2'), ('name3');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, name VARCHAR(255), price int);
INSERT INTO products (name, price) VALUES
('box1', 100),
('box2', 200),
('box3', 300);

DROP TABLE IF EXISTS customer_product CASCADE;
CREATE TABLE customer_product (id bigserial PRIMARY KEY, customer_id bigint references customers (id), product_id bigint references products (id), price int);
COMMIT;