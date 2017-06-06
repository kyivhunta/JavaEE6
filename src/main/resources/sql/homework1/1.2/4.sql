ALTER TABLE `homework`.`project` 

ADD COLUMN `cost` INT NOT NULL AFTER `name`;



UPDATE `homework`.`project` SET `cost`='1000000' WHERE `idProject`='1';

UPDATE `homework`.`project` SET `cost`='2000000' WHERE `idProject`='2';
