CREATE  TABLE `movies4us`.`FRIENDS` (
  `USER_ID` INT NOT NULL COMMENT 'user id of the user from the user info table' ,
  `GROUP_ID` INT NOT NULL COMMENT 'user id of the friend from the user info table' ,
  INDEX `friend_index` (`USER_ID` ASC) ,
  INDEX `fk_FRIENDS_1` (`USER_ID` ASC) ,
  INDEX `fk_FRIENDS_2` (`GROUP_ID` ASC) )
COMMENT = 'Stores the group ids of a user';

ALTER TABLE `movies4us`.`FRIENDS` 
ADD PRIMARY KEY (`USER_ID`, `GROUP_ID`) ;