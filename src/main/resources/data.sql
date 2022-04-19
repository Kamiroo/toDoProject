-- clean tables
DELETE FROM comment_entity;
DELETE From to_do;
DELETE FROM user_entity;

-- insert test data

INSERT INTO user_entity (user_id, email, first_name, last_name, login) VALUES (1, 'r.j.d@gmail.com', 'Rafal', 'Dominik', 'rafus');

INSERT INTO to_do (to_do_id, create_date, description, priority, status, title, update_date, user_id) VALUES (1, '2022-04-19', 'Pierwszy test', '0', '0', 'test 1', '2022-04-19', 1);
INSERT INTO to_do (to_do_id, create_date, description, priority, status, title, update_date, user_id) VALUES (2, '2022-04-19', 'Drugi test', '0', '1', 'test 2', '2022-04-19', 1);
