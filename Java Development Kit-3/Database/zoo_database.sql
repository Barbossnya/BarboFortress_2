CREATE DATABASE zoo_database;

CREATE TABLE animals (
    id SERIAL PRIMARY KEY,                       -- Уникальный идентификатор с автогенерацией
    species VARCHAR(100) NOT NULL,              -- Вид животного (ограничение на 100 символов)
    gender VARCHAR(10) CHECK (gender IN ('male', 'female')), -- Пол (только male или female)
    age INT CHECK (age >= 0)                    -- Возраст (неотрицательное значение)
);

Insert some sample data into the `animals` table:

INSERT INTO animals (species, gender, age) VALUES
('Lion', 'male', 5),
('Tiger', 'female', 3),
('Giraffe', 'male', 7),
('Elephant', 'female', 12),
('Elephant', 'male', 12),
('Tiger', 'male', 3),
('Lion', 'female', 8),
('Gorilla', 'male', 14),
('Orangutan', 'female', 6),
('Orangutan', 'male', 4),
('Orangutan', 'female', 5),
('Gorilla', 'female', 10),
('Gorilla', 'male', 8),
('Gorilla', 'female', 9),
('Gorilla', 'male', 11),
('Gorilla', 'female', 7),
('Ostrich', 'female', 5),
('Ostrich', 'male', 6),
('Ostrich', 'female', 7),
('Ostrich', 'male', 8),
('Ostrich', 'female', 9),
('Ostrich', 'male', 10),
('Ostrich', 'female', 11),
('Eagle', 'male', 4),
('Eagle', 'female', 5),
('Falcon', 'male', 3),
('Falcon', 'female', 4),
('Falcon', 'male', 5),
('Falcon', 'female', 6),
('Falcon', 'male', 7),
('Chimpanzee', 'female', 8),
('Chimpanzee', 'male', 9),
('Chimpanzee', 'female', 10),
('Chimpanzee', 'male', 11),
('Chimpanzee', 'female', 12),
('Chimpanzee', 'male', 13),
('Chimpanzee', 'female', 14),
('Chimpanzee', 'male', 15),
('Chimpanzee', 'female', 16),
('Chimpanzee', 'male', 17),
('Chimpanzee', 'female', 18),
('Chimpanzee', 'male', 19),
('Chimpanzee', 'female', 20),
('Dolphin', 'female', 10),
('Dolphin', 'male', 11),
('Dolphin', 'female', 12),
('Python', 'male', 15),
('Koala', 'female', 2),
('Koala', 'male', 3),
('Koala', 'female', 4),
('Koala', 'male', 5),
('Sloth', 'female', 3),
('Sloth', 'male', 4)



SELECT * FROM animals;

SELECT * FROM animals WHERE species = 'Lion';

UPDATE animals SET age = 6 WHERE id = 1;

DELETE FROM animals WHERE id = 1;

COMMIT;
