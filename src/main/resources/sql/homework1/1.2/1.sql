ALTER TABLE `homework`.`developer` 
ADD COLUMN `salary` INT NOT NULL AFTER `secondName`;


UPDATE `homework`.`developer` SET `salary`='15000' WHERE `id`='4';

UPDATE `homework`.`developer` SET `salary`='20000' WHERE `id`='5';

UPDATE `homework`.`developer` SET `salary`='12000' WHERE `id`='6';

UPDATE `homework`.`developer` SET `salary`='17000' WHERE `id`='7';
