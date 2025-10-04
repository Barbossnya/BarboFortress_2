# Демонстрация возможностей Maven

## Что мы реализовали в этом проекте:

### ✅ 1. Настройка Maven проекта
- **pom.xml** с полной конфигурацией
- **Версия Java 21** (обновлено с Java 11)
- **Структура проекта** согласно Maven conventions
- **Метаданные проекта** (groupId, artifactId, version, description)

### ✅ 2. Сторонние зависимости
- **Apache Commons Lang 3.12.0** - утилиты для работы со строками
- **SLF4J 2.0.7** - фасад для логирования
- **Logback 1.4.7** - реализация логирования
- **Jackson 2.15.2** - обработка JSON
- **JUnit 5.9.2** - тестирование

### ✅ 3. Maven плагины
- **maven-compiler-plugin** - компиляция Java 21
- **maven-enforcer-plugin** - проверка версий Maven и Java
- **maven-checkstyle-plugin** - проверка стиля кода
- **maven-surefire-plugin** - запуск тестов
- **jacoco-maven-plugin** - анализ покрытия кода
- **maven-shade-plugin** - создание исполняемого JAR
- **maven-javadoc-plugin** - генерация документации

### ✅ 4. Функциональность приложения
- **Демонстрация Apache Commons Lang**: reverse, trim, isEmpty, isBlank
- **Логирование**: разные уровни логов с ротацией файлов
- **JSON обработка**: создание и парсинг JSON
- **Тестирование**: unit тесты с параметризованными тестами

### ✅ 5. Документация и инструкции
- **README.md** - полное описание проекта
- **INSTALL.md** - инструкции по установке Maven
- **EXAMPLE_OUTPUT.md** - пример вывода программы
- **demo.bat** - скрипт для демонстрации

## Ключевые возможности Maven, которые мы продемонстрировали:

### 🔧 Управление зависимостями
```xml
<dependencies>
    <dependency>
        <groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.12.0</version>
    </dependency>
</dependencies>
```

### ⚙️ Настройка плагинов
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.11.0</version>
    <configuration>
        <source>21</source>
        <target>21</target>
    </configuration>
</plugin>
```

### 📦 Создание исполняемого JAR
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-shade-plugin</artifactId>
    <configuration>
        <transformers>
            <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                <mainClass>gb.intermediate_certification.App</mainClass>
            </transformer>
        </transformers>
    </configuration>
</plugin>
```

### 🧪 Автоматизация тестирования
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <includes>
            <include>**/*Test.java</include>
        </includes>
    </configuration>
</plugin>
```

### 📊 Анализ качества кода
```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <!-- Настройки для анализа покрытия кода -->
</plugin>
```

## Команды Maven для изучения:

```bash
# Сборка проекта
mvn clean compile

# Запуск тестов
mvn test

# Создание JAR
mvn clean package

# Проверка стиля кода
mvn checkstyle:check

# Анализ покрытия кода
mvn jacoco:report

# Генерация документации
mvn javadoc:javadoc

# Полный жизненный цикл
mvn clean install
```

## Преимущества использования Maven:

1. **Автоматизация сборки** - не нужно вручную управлять classpath
2. **Управление зависимостями** - автоматическое скачивание библиотек
3. **Стандартизация** - единая структура для всех проектов
4. **Интеграция с IDE** - поддержка в IntelliJ IDEA, Eclipse, VS Code
5. **Плагины** - богатая экосистема инструментов
6. **Жизненный цикл** - стандартные фазы сборки

## Заключение

Этот проект демонстрирует, что даже в простом "Hello World" приложении можно реализовать полноценную настройку Maven с:
- Сторонними библиотеками
- Настройкой плагинов
- Автоматизацией сборки
- Тестированием
- Анализом качества кода

Это показывает мощь и гибкость Maven как сборщика проектов. 