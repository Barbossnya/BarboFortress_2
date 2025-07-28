@echo off
echo Система учета питомника - BarboFortress_2
echo.

if "%1"=="compile" goto compile
if "%1"=="run" goto run
if "%1"=="clean" goto clean
if "%1"=="jar" goto jar
if "%1"=="help" goto help

echo Использование: build.bat [команда]
echo.
echo Доступные команды:
echo   compile - Компиляция Java файлов
echo   run     - Компиляция и запуск программы
echo   clean   - Очистка скомпилированных файлов
echo   jar     - Создание JAR файла
echo   help    - Показать эту справку
echo.
goto end

:compile
echo Компиляция Java файлов...
javac src\main\java\*.java
if %errorlevel% equ 0 (
    echo Компиляция завершена успешно!
) else (
    echo Ошибка компиляции!
)
goto end

:run
echo Компиляция и запуск программы...
call :compile
if %errorlevel% equ 0 (
    echo Запуск программы...
    java -cp src\main\java Main
) else (
    echo Не удалось запустить программу!
)
goto end

:clean
echo Очистка скомпилированных файлов...
del /q src\main\java\*.class 2>nul
echo Очистка завершена!
goto end

:jar
echo Создание JAR файла...
call :compile
if %errorlevel% equ 0 (
    cd src\main\java
    jar cvfe ../../../AnimalRegistry.jar Main *.class
    cd ..\..\..
    echo JAR файл создан: AnimalRegistry.jar
) else (
    echo Не удалось создать JAR файл!
)
goto end

:help
echo.
echo === СПРАВКА ===
echo.
echo Команды для работы с проектом:
echo.
echo   build.bat compile - Компиляция Java файлов
echo   build.bat run     - Компиляция и запуск программы
echo   build.bat clean   - Очистка скомпилированных файлов
echo   build.bat jar     - Создание JAR файла
echo   build.bat help    - Показать эту справку
echo.
echo Для Linux/Mac используйте: make [команда]
echo.

:end 