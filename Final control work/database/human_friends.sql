-- Создание базы данных Human Friends
CREATE DATABASE IF NOT EXISTS `HumanFriends`;
USE `HumanFriends`;

-- Создание таблицы для домашних животных (Pets)
CREATE TABLE IF NOT EXISTS `Pets` (
    `ID` INT AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(50) NOT NULL,
    `Type` ENUM('Dog', 'Cat', 'Hamster') NOT NULL,
    `BirthDate` DATE NOT NULL,
    `Commands` TEXT
);

-- Создание таблицы для вьючных животных (PackAnimals)
CREATE TABLE IF NOT EXISTS `PackAnimals` (
    `ID` INT AUTO_INCREMENT PRIMARY KEY,
    `Name` VARCHAR(50) NOT NULL,
    `Type` ENUM('Horse', 'Camel', 'Donkey') NOT NULL,
    `BirthDate` DATE NOT NULL,
    `Commands` TEXT
);

-- Заполнение таблицы Pets данными
INSERT INTO `Pets` (`Name`, `Type`, `BirthDate`, `Commands`) VALUES
('Fido', 'Dog', '2020-01-01', 'Sit, Stay, Fetch'),
('Whiskers', 'Cat', '2019-05-15', 'Sit, Pounce'),
('Hammy', 'Hamster', '2021-03-10', 'Roll, Hide'),
('Buddy', 'Dog', '2018-12-10', 'Sit, Paw, Bark'),
('Smudge', 'Cat', '2020-02-20', 'Sit, Pounce, Scratch'),
('Peanut', 'Hamster', '2021-08-01', 'Roll, Spin'),
('Bella', 'Dog', '2019-11-11', 'Sit, Stay, Roll'),
('Oliver', 'Cat', '2020-06-30', 'Meow, Scratch, Jump');

-- Заполнение таблицы PackAnimals данными
INSERT INTO `PackAnimals` (`Name`, `Type`, `BirthDate`, `Commands`) VALUES
('Thunder', 'Horse', '2015-07-21', 'Trot, Canter, Gallop'),
('Sandy', 'Camel', '2016-11-03', 'Walk, Carry Load'),
('Eeyore', 'Donkey', '2017-09-18', 'Walk, Carry Load, Bray'),
('Storm', 'Horse', '2014-05-05', 'Trot, Canter'),
('Dune', 'Camel', '2018-12-12', 'Walk, Sit'),
('Burro', 'Donkey', '2019-01-23', 'Walk, Bray, Kick'),
('Blaze', 'Horse', '2016-02-29', 'Trot, Jump, Gallop'),
('Sahara', 'Camel', '2015-08-14', 'Walk, Run');

-- Удаление записей о верблюдах
DELETE FROM `PackAnimals` WHERE `Type` = 'Camel';

-- Создание новой таблицы для животных в возрасте от 1 до 3 лет
CREATE TABLE IF NOT EXISTS `YoungAnimals` AS
SELECT 
    ID,
    Name,
    Type,
    BirthDate,
    Commands,
    TIMESTAMPDIFF(MONTH, BirthDate, CURDATE()) AS AgeInMonths
FROM (
    SELECT ID, Name, Type, BirthDate, Commands FROM `Pets`
    UNION ALL
    SELECT ID, Name, Type, BirthDate, Commands FROM `PackAnimals`
) AS AllAnimals
WHERE TIMESTAMPDIFF(YEAR, BirthDate, CURDATE()) BETWEEN 1 AND 3;

-- Объединение всех таблиц в одну
CREATE TABLE IF NOT EXISTS `AllAnimals` AS
SELECT 
    ID,
    Name,
    Type,
    BirthDate,
    Commands,
    'Pet' AS Category
FROM `Pets`
UNION ALL
SELECT 
    ID,
    Name,
    Type,
    BirthDate,
    Commands,
    'Pack Animal' AS Category
FROM `PackAnimals`;

-- Показать результаты
SELECT 'Pets' AS TableName, COUNT(*) AS Count FROM `Pets`
UNION ALL
SELECT 'PackAnimals' AS TableName, COUNT(*) AS Count FROM `PackAnimals`
UNION ALL
SELECT 'YoungAnimals' AS TableName, COUNT(*) AS Count FROM `YoungAnimals`
UNION ALL
SELECT 'AllAnimals' AS TableName, COUNT(*) AS Count FROM `AllAnimals`; 