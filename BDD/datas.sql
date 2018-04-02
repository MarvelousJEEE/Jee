-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Players`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Players` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Players` (
  `pseudo` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `birthday` DATE NULL,
  `email` VARCHAR(45) NOT NULL,
  `ban` TINYINT(1) NULL DEFAULT 0,
  `subscription` DATE NULL,
  PRIMARY KEY (`pseudo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Games`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Games` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Games` (
  `name` VARCHAR(45) NOT NULL,
  `infos` TEXT(500) NULL,
  `release` DATE NULL,
  `show` TINYINT(1) NULL DEFAULT 1,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`PreferedGames`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`PreferedGames` ;

CREATE TABLE IF NOT EXISTS `mydb`.`PreferedGames` (
  `pseudo` VARCHAR(20) NOT NULL,
  `gameName` VARCHAR(45) NOT NULL,
  INDEX `fk_PreferedGames_Player1_idx` (`pseudo` ASC),
  INDEX `fk_PreferedGames_Games1_idx` (`gameName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Matchs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Matchs` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Matchs` (
  `idMatch` INT NOT NULL,
  `pseudo` VARCHAR(20) NOT NULL,
  `gameName` VARCHAR(45) NOT NULL,
  `hBegin` DATETIME NULL,
  `hEnd` DATETIME NULL,
  PRIMARY KEY (`idMatch`),
  INDEX `fk_Matchs_Player1_idx` (`pseudo` ASC),
  INDEX `fk_Matchs_Games1_idx` (`gameName` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`Players`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Players` (`pseudo`, `password`, `birthday`, `email`, `ban`, `subscription`) VALUES ('MacFly', '456', '1999-05-15', 'mcfly@gmail.com', 0, '2018-03-12');
INSERT INTO `mydb`.`Players` (`pseudo`, `password`, `birthday`, `email`, `ban`, `subscription`) VALUES ('MacChouffe', '213', '1995-03-12', 'macchouffe@gmal.com', 0, '2018-03-13');
INSERT INTO `mydb`.`Players` (`pseudo`, `password`, `birthday`, `email`, `ban`, `subscription`) VALUES ('MacGros', '789', '1996-04-11', 'macggros@gmail.com', 0, '2018-03-15');
INSERT INTO `mydb`.`Players` (`pseudo`, `password`, `birthday`, `email`, `ban`, `subscription`) VALUES ('Louis', '123', '1996-04-11', 'loui@gmail.com', 0, '2018-03-16');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Games`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Games` (`name`, `infos`, `release`, `show`) VALUES ('3', 'Zelda', '2015-09-15', 1);
INSERT INTO `mydb`.`Games` (`name`, `infos`, `release`, `show`) VALUES ('2', 'Mario ', '2016-11-25', 1);
INSERT INTO `mydb`.`Games` (`name`, `infos`, `release`, `show`) VALUES ('1', 'Sonic ', '2017-3-29', 1);
INSERT INTO `mydb`.`Games` (`name`, `infos`, `release`, `show`) VALUES ('4', 'Pokemon', '1996-09-15', 1);
INSERT INTO `mydb`.`Games` (`name`, `infos`, `release`, `show`) VALUES ('5', 'Yu-Gi-Ho', '2005-09-15', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`PreferedGames`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`PreferedGames` (`pseudo`, `gameName`) VALUES ('MacChouffe', 'Game1');
INSERT INTO `mydb`.`PreferedGames` (`pseudo`, `gameName`) VALUES ('MacChouffe', 'Game2');
INSERT INTO `mydb`.`PreferedGames` (`pseudo`, `gameName`) VALUES ('MacFly', 'Game3');
INSERT INTO `mydb`.`PreferedGames` (`pseudo`, `gameName`) VALUES ('MacGros', 'Game1');
INSERT INTO `mydb`.`PreferedGames` (`pseudo`, `gameName`) VALUES ('MacGros', 'Game3');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Matchs`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Matchs` (`idMatch`, `pseudo`, `gameName`, `hBegin`, `hEnd`) VALUES (1, 'Louis', 'Zelda', '2018-03-15 12:50:12', '2018-03-15 13:50:12');
INSERT INTO `mydb`.`Matchs` (`idMatch`, `pseudo`, `gameName`, `hBegin`, `hEnd`) VALUES (2 , 'MacFly', 'Mario ', '2018-03-15 12:55:12', '2018-03-15 14:50:12');
INSERT INTO `mydb`.`Matchs` (`idMatch`, `pseudo`, `gameName`, `hBegin`, `hEnd`) VALUES (3, 'MacFly ', 'Mario ', '2017-03-15 16:50:12', '2014-03-15 18:50:12');
INSERT INTO `mydb`.`Matchs` (`idMatch`, `pseudo`, `gameName`, `hBegin`, `hEnd`) VALUES (4, 'Louis ', 'Sonic ', '2018-03-16 12:50:12', '2018-03-15 12:55:12');
INSERT INTO `mydb`.`Matchs` (`idMatch`, `pseudo`, `gameName`, `hBegin`, `hEnd`) VALUES (5, 'Louis ', 'Zelda', '2018-03-20 12:50:12', NULL);
INSERT INTO `mydb`.`Matchs` (`idMatch`, `pseudo`, `gameName`, `hBegin`, `hEnd`) VALUES (6, 'MacGros', 'Mario', '2018-03-20 14:50:12', NULL);
INSERT INTO `mydb`.`Matchs` (`idMatch`, `pseudo`, `gameName`, `hBegin`, `hEnd`) VALUES (7, 'MacFly', 'Sonic ', '2018-03-21 13:50:12', NULL);

COMMIT;


