CREATE  TABLE `movies4us`.`MOVIES` (
  `MOVIE_ID` INT NOT NULL COMMENT 'movie id is the primary key' ,
  `MOVIE_NAME` VARCHAR(45) NOT NULL COMMENT 'name of the movie. movie name can be same but combination of movie name and year of release is unique' ,
  `RELEASE_YEAR` VARCHAR(4) NOT NULL COMMENT 'year of release' ,
  PRIMARY KEY (`MOVIE_ID`) ,
  UNIQUE INDEX `MOVIE_ID_UNIQUE` (`MOVIE_NAME` ASC, `RELEASE_YEAR` ASC) )
COMMENT = 'Stores the movie information.';