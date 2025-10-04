# Java Development Kit 6 - Maven Project

Этот проект демонстрирует настройку Maven проекта с использованием сторонних библиотек и различных плагинов.

## Описание проекта

Проект создан для изучения сборщиков проектов Maven. Включает в себя:
- Настройку зависимостей от сторонних библиотек
- Конфигурацию плагинов для сборки, тестирования и анализа кода
- Демонстрацию использования различных библиотек

## Используемые технологии

### Основные зависимости
- **Apache Commons Lang 3.12.0** - утилиты для работы со строками
- **SLF4J 2.0.7** - фасад для логирования
- **Logback 1.4.7** - реализация логирования
- **Jackson 2.15.2** - обработка JSON
- **JUnit 5.9.2** - тестирование

### Maven плагины
- **maven-compiler-plugin** - компиляция Java 11
- **maven-enforcer-plugin** - проверка версий Maven и Java
- **maven-checkstyle-plugin** - проверка стиля кода
- **maven-surefire-plugin** - запуск тестов
- **jacoco-maven-plugin** - анализ покрытия кода
- **maven-shade-plugin** - создание исполняемого JAR
- **maven-javadoc-plugin** - генерация документации

## Структура проекта

```
java_development_kit_6/
├── src/
│   ├── main/
│   │   ├── java/gb/intermediate_certification/
│   │   │   └── App.java
│   │   └── resources/
│   │       └── logback.xml
│   └── test/
│       └── java/gb/intermediate_certification/
│           └── AppTest.java
├── pom.xml
└── README.md
```

## Команды Maven

### Сборка проекта
```bash
mvn clean compile
```

### Запуск тестов
```bash
mvn test
```

### Создание исполняемого JAR
```bash
mvn clean package
```

### Запуск приложения
```bash
# После сборки
java -jar target/java_development_kit_6-1.0.0.jar

# Или через Maven
mvn exec:java -Dexec.mainClass="gb.intermediate_certification.App"
```

### Проверка стиля кода
```bash
mvn checkstyle:check
```

### Генерация отчета о покрытии кода
```bash
mvn jacoco:report
```

### Генерация документации
```bash
mvn javadoc:javadoc
```

## Функциональность

Приложение демонстрирует:

1. **Apache Commons Lang** - работа со строками (reverse, trim, isEmpty, isBlank)
2. **Логирование** - использование SLF4J + Logback для записи логов
3. **JSON обработка** - создание и парсинг JSON с помощью Jackson
4. **Тестирование** - unit тесты с JUnit 5, включая параметризованные тесты

## Требования

- Java 21 или выше
- Maven 3.6.3 или выше

## Настройки

### Версия Java
Проект настроен для работы с Java 21:
```xml
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
```

### Логирование
Настроено логирование в файл `logs/application.log` и консоль с ротацией файлов.

### Покрытие кода
JaCoCo настроен для анализа покрытия кода с возможностью установки пороговых значений.

## Демонстрация возможностей Maven

Этот проект показывает:
- Управление зависимостями
- Настройку плагинов
- Автоматизацию сборки
- Интеграцию с инструментами разработки
- Создание исполняемых артефактов 