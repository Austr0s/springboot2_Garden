CREATE DATABASE IF NOT EXISTS `gardendb`;
CREATE USER IF NOT EXISTS garden_admin@localhost IDENTIFIED BY 'admin';
GRANT USAGE ON gardendb.* TO garden_admin@localhost IDENTIFIED BY 'admin';
GRANT ALL privileges ON `gardendb`.* TO garden_admin@localhost;

COMMIT;

