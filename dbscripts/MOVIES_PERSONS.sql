CREATE  TABLE `movies4us`.`MOVIES_PERSONS` (
  `movie_id` INT NOT NULL ,
  `release_year` VARCHAR(4) NOT NULL ,
  `person_name` VARCHAR(45) NULL ,
  `person_role` VARCHAR(2) NULL ,
  PRIMARY KEY (`movie_id`, `release_year`) ,
  INDEX `mov_person` (`movie_id` ASC, `release_year` ASC, `person_name` ASC) )
COMMENT = 'information about the actors,actresses, directors,etc ';


ALTER TABLE `movies4us`.`MOVIES_PERSONS` 
  ADD CONSTRAINT `fk_MOVIES_PERSONS_1`
  FOREIGN KEY (`movie_id` )
  REFERENCES `movies4us`.`MOVIES` (`MOVIE_ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION
, ADD INDEX `fk_MOVIES_PERSONS_1` (`movie_id` ASC) ;


ALTER TABLE `movies4us`.`MOVIES_PERSONS` DROP COLUMN `release_year` 
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`movie_id`) 
, DROP INDEX `mov_person` 
, ADD INDEX `mov_person` (`movie_id` ASC, `person_name` ASC) ;

ALTER TABLE `movies4us`.`MOVIES_PERSONS` CHANGE COLUMN `person_name` `person_name` VARCHAR(45) NOT NULL DEFAULT 'unknown'  
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`movie_id`, `person_name`) ;

ALTER TABLE `movies4us`.`MOVIES_PERSONS` CHANGE COLUMN `person_role` `person_role` VARCHAR(2) NOT NULL DEFAULT 'na'  
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`movie_id`, `person_name`, `person_role`) ;
