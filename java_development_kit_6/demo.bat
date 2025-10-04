@echo off
echo ========================================
echo Java Development Kit 6 - Maven Demo
echo ========================================
echo.

echo Проверка Java...
java -version
echo.

echo Проверка Maven...
mvn -version 2>nul
if %errorlevel% neq 0 (
    echo Maven не найден. Установите Maven согласно INSTALL.md
    echo.
    echo Альтернативные способы запуска:
    echo 1. Используйте IDE (IntelliJ IDEA, Eclipse, VS Code)
    echo 2. Установите Maven: https://maven.apache.org/download.cgi
    echo 3. Используйте Docker: docker run --rm -v %cd%:/app -w /app maven:3.9.5-openjdk-21 mvn clean compile
    echo.
    pause
    exit /b 1
)

echo.
echo Компиляция проекта...
mvn clean compile

echo.
echo Запуск тестов...
mvn test

echo.
echo Создание JAR файла...
mvn clean package

echo.
echo Запуск приложения...
java -jar target/java_development_kit_6-1.0.0.jar

echo.
echo Демонстрация завершена!
pause 