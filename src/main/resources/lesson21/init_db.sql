BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial PRIMARY KEY, name VARCHAR(255), age int);
INSERT INTO users (name, age) VALUES
('user1', 10), ('user2', 15), ('user3', 20), ('user4', 25);

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products(id bigserial PRIMARY KEY, name VARCHAR(255), price int);
INSERT INTO products (name, price) VALUES
('item1', 100),
('item2', 200),
('item3', 300),
('item4', 400);

COMMIT;