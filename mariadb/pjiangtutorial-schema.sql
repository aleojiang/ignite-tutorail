create database testservice;
GRANT ALL PRIVILEGES ON testservice.* TO 'mysqluser'@'%' WITH GRANT OPTION;

USE testservice;

DROP table IF EXISTS users;

create table users (
  id BIGINT PRIMARY KEY,
  first_name nvarchar(1000),
  last_name nvarchar(1000),
  dob date,
  gender varchar(10),
);

CREATE INDEX users_name_idx ON users (first_name, last_name);
CREATE INDEX users_gender_idx ON users (gender);


