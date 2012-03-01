CREATE  TABLE `movies4us`.`RATINGS` (
  `USER_ID` INT NOT NULL COMMENT 'user id from user info table' ,
  `MOVIE_ID` INT NOT NULL COMMENT 'movie id from movies table' ,
  `RATINGS` INT NOT NULL COMMENT 'user ratings for movies' ,
  `RATING_DATE` DATETIME NULL COMMENT 'date when rating given' ,
  INDEX `rating_index` (`USER_ID` ASC, `MOVIE_ID` ASC) ,
  INDEX `rating_uid_fk` (`USER_ID` ASC) ,
  INDEX `rating_mid_fk` (`MOVIE_ID` ASC) )
COMMENT = 'Stores rating information for user-movie combination';

ALTER TABLE `movies4us`.`RATINGS` 
  ADD CONSTRAINT `fk_RATINGS_1`
  FOREIGN KEY (`USER_ID` )
  REFERENCES `movies4us`.`USER_INFO` (`USER_ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION, 
  ADD CONSTRAINT `fk_RATINGS_2`
  FOREIGN KEY (`MOVIE_ID` )
  REFERENCES `movies4us`.`MOVIES` (`MOVIE_ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `fk_RATINGS_1` (`USER_ID` ASC) 
, ADD INDEX `fk_RATINGS_2` (`MOVIE_ID` ASC) 
, DROP INDEX `rating_mid_fk` 
, DROP INDEX `rating_uid_fk` ;

ALTER TABLE `movies4us`.`RATINGS` CHANGE COLUMN `RATINGS` `RATINGS` INT(11) NULL COMMENT 'user ratings for movies'  
, ADD PRIMARY KEY (`USER_ID`, `MOVIE_ID`) ;

ALTER TABLE `movies4us`.`RATINGS` CHANGE COLUMN `RATING_DATE` `RATING_DATE` TIMESTAMP NULL DEFAULT NULL COMMENT 'date when rating given'  ;

ALTER TABLE `movies4us`.`RATINGS` CHANGE COLUMN `RATINGS` `RATINGS` DECIMAL(3,1) NULL DEFAULT 0 COMMENT 'user ratings for movies'  ;