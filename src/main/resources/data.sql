DROP TABLE IF EXISTS users;
CREATE TABLE users (id bigint not null auto_increment, age integer, name varchar(255), lastName varchar(255), primary key (id)) ENGINE=InnoDB;
INSERT INTO users (age, name, lastName) VALUES (29, 'Vasily', 'Grachevsky'), (34, 'James', 'Bond'), (64, 'Petrov', 'Dmitry'), (19, 'Anna', 'Milostivaya'), (5, 'BMV', 'X-Series');