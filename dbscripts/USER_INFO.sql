CREATE  TABLE `movies4us`.`USER_INFO` (
  `USER_ID` INT NOT NULL COMMENT 'User Id is the primary key' ,
  `USERNAME` VARCHAR(20) NOT NULL COMMENT 'username with which the user registers' ,
  `PASSWORD` VARCHAR(10) NULL COMMENT 'password with which the user registers' ,
  `JOIN_DATE` DATETIME NULL COMMENT 'date time when the user registers' ,
  PRIMARY KEY (`USER_ID`) ,
  UNIQUE INDEX `USERNAME_UNIQUE` (`USERNAME` ASC) )
COMMENT = 'Stores the information of all users registered in the system';

ALTER TABLE `movies4us`.`USER_INFO` CHANGE COLUMN `JOIN_DATE` `JOIN_DATE` TIMESTAMP NULL DEFAULT NULL COMMENT 'date time when the user registers'  ;