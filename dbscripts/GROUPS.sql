CREATE  TABLE `movies4us`.`GROUPS` (
  `GROUP_ID` INT NOT NULL ,
  `GROUP_NAME` VARCHAR(20) NULL ,
  `MEMBER_USER_ID` INT NULL ,
  PRIMARY KEY (`GROUP_ID`) ,
  INDEX `GRP_USR_ID` (`MEMBER_USER_ID` ASC) ,
  INDEX `fk_GROUPS_USR_ID` (`MEMBER_USER_ID` ASC) ,
  CONSTRAINT `fk_GROUPS_USR_ID`
    FOREIGN KEY (`MEMBER_USER_ID` )
    REFERENCES `movies4us`.`USER_INFO` (`USER_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

ALTER TABLE `movies4us`.`GROUPS` DROP FOREIGN KEY `fk_GROUPS_USR_ID` ;
ALTER TABLE `movies4us`.`GROUPS` DROP COLUMN `MEMBER_USER_ID` ;
ALTER TABLE `movies4us`.`GROUPS` ADD GROUP_ADMIN INT
, DROP INDEX `GRP_USR_ID` ;