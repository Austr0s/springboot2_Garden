CREATE DATABASE `gardendb`;
CREATE USER IF NOT EXISTS garden_admin@localhost IDENTIFIED BY 'admin';
GRANT USAGE ON *.* TO garden_admin@localhost IDENTIFIED BY 'admin';
GRANT ALL privileges ON `gardendb`.* TO garden_admin@localhost;