CREATE DATABASE phonebook_database;
-- \c phonebook_database

CREATE TABLE humans (
    id SERIAL PRIMARY KEY,
    lastName VARCHAR(100) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    middleName VARCHAR(100),
    gender VARCHAR(10) CHECK (gender IN ('male', 'female')),
    birthdate DATE NOT NULL,
    phonenumber VARCHAR(20)
);

INSERT INTO humans (lastName, firstName, middleName, gender, birthdate, phonenumber) VALUES
('Столотов', 'Иван', 'Викторович', 'male', '1991-03-21', '742344446244'),
('Лебедева', 'Мария', 'Сергеевна', 'female', '1988-08-12', '84964646244'),
('Бакуленко', 'Алиса', 'Алексеевна', 'female', '1999-01-18', '79216165144'),
('Разумовский', 'Михаил', 'Семенович', 'male', '1979-11-09', '89498946244');

-- Пример через TO_DATE для уникального формата
INSERT INTO humans (lastName, firstName, middleName, gender, birthdate, phonenumber) VALUES
('Лоскутов', 'Пётр', 'Ильич', 'male', TO_DATE('05.05.2000', 'DD.MM.YYYY'), '71234567890');

-- Получить всех с возрастом:
SELECT *,
        DATE_PART('year', AGE(birthdate)) AS age
FROM humans;

-- Поиск по фамилии:
SELECT * FROM humans WHERE lastName = 'Столотов';

-- Пример обновления даты рождения (а не возраста!)
UPDATE humans SET birthdate = '1990-03-21' WHERE id = 1;

-- Удалить запись:
DELETE FROM humans WHERE id = 2;