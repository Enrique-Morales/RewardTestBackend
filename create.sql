-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema review_insight_enrique
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema review_insight_enrique
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `review_insight_enrique` DEFAULT CHARACTER SET utf8 ;
USE `review_insight_enrique` ;

-- -----------------------------------------------------
-- Table `review_insight_enrique`.`usertype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `review_insight_enrique`.`usertype` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `desc` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `review_insight_enrique`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `review_insight_enrique`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(255) NULL,
  `lastName` VARCHAR(255) NULL,
  `email` VARCHAR(255) NULL,
  `phone` VARCHAR(255) NULL,
  `birthDate` DATE NULL,
  `usertype_id` INT NULL,
  `password` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `review_insight_enrique`.`reviewlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `review_insight_enrique`.`reviewlist` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL,
  `author_id` INT NULL,
  `employee_id` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `review_insight_enrique`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `review_insight_enrique`.`review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rating` TINYINT(2) NULL,
  `comment` LONGTEXT NULL,
  `submitted` TINYINT(1) NULL,
  `user_id` INT NULL,
  `reviewlist_id` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;