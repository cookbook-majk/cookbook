CREATE DATABASE IF NOT EXISTS cookbook_db;

USE cookbook_db;

CREATE USER IF NOT EXISTS 'cookbook_user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL ON cookbook_db.* TO 'cookbook_user'@'localhost' WITH GRANT OPTION;

SHOW grants for 'cookbook_user'@'localhost';