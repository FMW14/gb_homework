BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO users (name) VALUES
('user1'), ('user2'), ('user3'), ('user4'), ('user5'), ('user6'), ('user7'), ('user8');

DROP TABLE IF EXISTS lots CASCADE;
CREATE TABLE lots(id bigserial PRIMARY KEY, name VARCHAR(255), bet int, user_id bigint references users (id), version bigint);
INSERT INTO lots (name, bet, version) VALUES
('lot1', 0, 0),
('lot2', 0, 0),
('lot3', 0, 0),
('lot4', 0, 0);

COMMIT;