CREATE  TABLE `movies4us`.`FRIENDS` (
  `USER_ID` INT NOT NULL COMMENT 'user id of the user from the user info table' ,
  `GROUP_ID` INT NOT NULL COMMENT 'user id of the friend from the user info table' ,
  INDEX `friend_index` (`USER_ID` ASC) ,
  INDEX `fk_FRIENDS_1` (`USER_ID` ASC) ,
  INDEX `fk_FRIENDS_2` (`GROUP_ID` ASC) )
COMMENT = 'Stores the group ids of a user';

ALTER TABLE `movies4us`.`FRIENDS` 
ADD PRIMARY KEY (`USER_ID`, `GROUP_ID`) ;

ALTER TABLE `movies4us`.`FRIENDS` 
  ADD CONSTRAINT `fk_FRIENDS_1`
  FOREIGN KEY (`GROUP_ID` )
  REFERENCES `movies4us`.`GROUPS` (`GROUP_ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION, 
  ADD CONSTRAINT `fk_FRIENDS_2`
  FOREIGN KEY (`USER_ID` )
  REFERENCES `movies4us`.`USER_INFO` (`USER_ID` )
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;