CREATE TABLE `movies4us`.`USER_PROFILE` (
`USER_ID` INT NOT NULL,
`ACTION` INT DEFAULT 0,
`ADVENTURE` INT DEFAULT 0,
`ANIMATION` INT DEFAULT 0,
`CHILDREN` INT DEFAULT 0,
`COMEDY` INT DEFAULT 0,
`CRIME` INT DEFAULT 0,
`DOCUMENTARY` INT DEFAULT 0,
`DRAMA` INT DEFAULT 0,
`FANTASY` INT DEFAULT 0,
`FILM-NOIR` INT DEFAULT 0,
`HORROR` INT DEFAULT 0,
`IMAX` INT DEFAULT 0,
`MUSICAl` INT DEFAULT 0,
`MYSTERY` INT DEFAULT 0,
`ROMANCE` INT DEFAULT 0,
`SCI-FI` INT DEFAULT 0,
`THRILLER` INT DEFAULT 0,
`WAR` INT DEFAULT 0,
`WESTERN` INT DEFAULT 0,

PRIMARY KEY(`USER_ID`),
FOREIGN KEY (`USER_ID` ) REFERENCES `movies4us`.`USER_INFO` (`USER_ID` )
)