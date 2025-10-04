# Настройка базы данных PostgreSQL

## 1. Установка PostgreSQL

### Windows:
1. Скачайте PostgreSQL с официального сайта: https://www.postgresql.org/download/windows/
2. Запустите установщик и следуйте инструкциям
3. Запомните пароль для пользователя `postgres`

### macOS:
```bash
brew install postgresql
brew services start postgresql
```

### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install postgresql postgresql-contrib
sudo systemctl start postgresql
sudo systemctl enable postgresql
```

## 2. Создание базы данных

### Подключение к PostgreSQL:
```bash
# Windows (если PostgreSQL добавлен в PATH)
psql -U postgres

# macOS/Linux
sudo -u postgres psql
```

### Создание базы данных:
```sql
CREATE DATABASE phonebook_database;
```

### Подключение к созданной базе:
```sql
\c phonebook_database
```

## 3. Выполнение SQL скрипта

Скопируйте и выполните содержимое файла `src/main/java/gb/jdk_5/Database/phonebook_database.sql`:

```sql
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
```

## 4. Настройка подключения в приложении

Отредактируйте файл `src/main/java/gb/jdk_5/Controller/PersonController.java`:

```java
String url = "jdbc:postgresql://localhost:5432/phonebook_database";
String user = "postgres"; // Ваш пользователь PostgreSQL
String password = "ваш_пароль"; // Пароль, который вы указали при установке
```

## 5. Проверка подключения

### Проверка таблицы:
```sql
SELECT * FROM humans;
```

### Проверка структуры таблицы:
```sql
\d humans
```

## Возможные проблемы

### 1. Ошибка "Connection refused"
- Убедитесь, что PostgreSQL запущен
- Проверьте, что порт 5432 не занят другим приложением

### 2. Ошибка "Authentication failed"
- Проверьте правильность имени пользователя и пароля
- Убедитесь, что пользователь имеет права на базу данных

### 3. Ошибка "Database does not exist"
- Создайте базу данных командой `CREATE DATABASE phonebook_database;`

### 4. Ошибка "Table does not exist"
- Выполните SQL скрипт для создания таблицы

## Полезные команды PostgreSQL

```sql
-- Список всех баз данных
\l

-- Список всех таблиц в текущей базе
\dt

-- Выход из psql
\q

-- Подключение к конкретной базе
\c phonebook_database
``` 