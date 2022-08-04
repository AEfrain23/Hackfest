CREATE SCHEMA IF NOT EXISTS `hackfest`;

USE `hackfest` ;


CREATE TABLE IF NOT EXISTS `hackfest`.`customers` (
    `id` INT(15) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(35) DEFAULT NULL,
    `surname` VARCHAR(35) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `hackfest`.`items` (
    `id` INT(15) NOT NULL AUTO_INCREMENT,
    `itemname` VARCHAR(35) NOT NULL,
    `description` VARCHAR(250) NOT NULL,
    `price` DECIMAL(15,2) NOT NULL,
    `stock` INT(15) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `hackfest`.`orders` (
    `id` INT(15) NOT NULL AUTO_INCREMENT,
    `fk_customer_id` INT(15) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY(fk_customer_id) REFERENCES customers(id)
    );
    
    CREATE TABLE IF NOT EXISTS `hackfest`.`orderdetails` (
    `id` INT(15) NOT NULL AUTO_INCREMENT,
    `fk_order_id` INT(15) NOT NULL,
    `fk_item_id` INT(15) NOT NULL,
    `quantity` INT(15),
    FOREIGN KEY(fk_item_id) REFERENCES items(id),
    FOREIGN KEY(fk_order_id) REFERENCES orders(id),
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `hackfest`.`delivery` (
`id` INT(15) NOT NULL AUTO_INCREMENT,
`fk_order_id` INT(15) NOT NULL,
FOREIGN KEY(fk_order_id) REFERENCES orders(id),
PRIMARY KEY (`id`)
);