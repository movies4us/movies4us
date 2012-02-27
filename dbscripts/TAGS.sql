CREATE  TABLE `movies4us`.`TAGS` (
  `USER_ID` INT NOT NULL COMMENT 'user id from the user info table' ,
  `MOVIE_ID` INT NOT NULL COMMENT 'movie id from the movies table' ,
  `TAG` VARCHAR(45) NOT NULL COMMENT 'user generated tag information for the movies' ,
  `TAG_DATE` DATETIME NULL COMMENT 'date of generated tag' ,
  INDEX `tag_index` (`USER_ID` ASC, `MOVIE_ID` ASC) ,
  INDEX `tags_uid_fk` (`USER_ID` ASC) ,
  INDEX `tags_mid_fk` (`MOVIE_ID` ASC) )
COMMENT = 'Contains user given tags for movies';

ALTER TABLE `movies4us`.`TAGS` 
  ADD CONSTRAINT `fk_TAGS_1`
  FOREIGN KEY (`USER_ID` )
  REFERENCES `movies4us`.`USER_INFO` (`USER_ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION, 
  ADD CONSTRAINT `fk_TAGS_2`
  FOREIGN KEY (`MOVIE_ID` )
  REFERENCES `movies4us`.`MOVIES` (`MOVIE_ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `fk_TAGS_1` (`USER_ID` ASC) 
, ADD INDEX `fk_TAGS_2` (`MOVIE_ID` ASC) 
, DROP INDEX `tags_mid_fk` 
, DROP INDEX `tags_uid_fk` ;