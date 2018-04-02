-- MySQL Script generated by MySQL Workbench
-- mer. 28 mars 2018 10:49:52 CEST
-- Model: New Model    Version: 1.0
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
  INDEX `fk_PreferedGames_Games1_idx` (`gameName` ASC),
  CONSTRAINT `fk_PreferedGames_Player1`
    FOREIGN KEY (`pseudo`)
    REFERENCES `mydb`.`Players` (`pseudo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_PreferedGames_Games1`
    FOREIGN KEY (`gameName`)
    REFERENCES `mydb`.`Games` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Matchs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Matchs` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Matchs` (
  `idMatch` INT NOT NULL AUTO_INCREMENT,
  `pseudo` VARCHAR(20) NOT NULL,
  `gameName` VARCHAR(45) NOT NULL,
  `hBegin` TIME(0) NOT NULL,
  `hEnd` TIME(0) NULL,
  PRIMARY KEY (`idMatch`),
  INDEX `fk_Matchs_Player1_idx` (`pseudo` ASC),
  INDEX `fk_Matchs_Games1_idx` (`gameName` ASC),
  CONSTRAINT `fk_Matchs_Player1`
    FOREIGN KEY (`pseudo`)
    REFERENCES `mydb`.`Players` (`pseudo`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Matchs_Games1`
    FOREIGN KEY (`gameName`)
    REFERENCES `mydb`.`Games` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `mydb`.`Players`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Players` (`pseudo`, `password`, `birthday`, `email`) VALUES ('MacFly', '456', '1999-05-15', 'mcfly@gmail.com');
INSERT INTO `mydb`.`Players` (`pseudo`, `password`, `birthday`, `email`) VALUES ('MacChouffe', '213', '1995-03-12', 'macchouffegmal.com');
INSERT INTO `mydb`.`Players` (`pseudo`, `password`, `birthday`, `email`) VALUES ('MacGros', '789', '1996-08-16', 'macgros@gmail.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Games`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Games` (`name`, `infos`, `release`) VALUES ('Game1', 'Voici le premier jeu de ma collection', '2015-09-15');
INSERT INTO `mydb`.`Games` (`name`, `infos`, `release`) VALUES ('Game2', NULL, '2016-11-25');
INSERT INTO `mydb`.`Games` (`name`, `infos`, `release`) VALUES ('Game3', NULL, '2017-3-29');

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
INSERT INTO `mydb`.`PreferedGames` (`pseudo`, `gameName`) VALUES ('MacGros', 'Game2');
INSERT INTO `mydb`.`PreferedGames` (`pseudo`, `gameName`) VALUES ('MacGros', 'Game3');

COMMIT;


-- -----------------------------------------------------
-- Data for table `mydb`.`Matchs`
-- -----------------------------------------------------
START TRANSACTION;
USE `mydb`;
INSERT INTO `mydb`.`Matchs` (`idMatch`, `pseudo`, `gameName`, `hBegin`, `hEnd`) VALUES (1, 'MacFly', 'Game1', '12:30:55', '12:45:35');
INSERT INTO `mydb`.`Matchs` (`idMatch`, `pseudo`, `gameName`, `hBegin`, `hEnd`) VALUES (2, 'MacFly', 'Game2', '10:23:36', '11:28:13');
INSERT INTO `mydb`.`Matchs` (`idMatch`, `pseudo`, `gameName`, `hBegin`, `hEnd`) VALUES (3, 'MacChouffe', 'Game3', '08:24:08', '08:25:36');

COMMIT;

