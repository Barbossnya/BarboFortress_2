@echo off
echo Проверка проекта телефонного справочника...
echo.

echo 1. Проверка структуры проекта...
if exist "src\main\java\gb\jdk_5\App.java" (
    echo ✓ App.java найден
) else (
    echo ✗ App.java не найден
)

if exist "src\main\java\gb\jdk_5\Model\Person.java" (
    echo ✓ Person.java найден
) else (
    echo ✗ Person.java не найден
)

if exist "src\main\java\gb\jdk_5\Controller\PersonController.java" (
    echo ✓ PersonController.java найден
) else (
    echo ✗ PersonController.java не найден
)

if exist "src\main\java\gb\jdk_5\View\PersonMenu.java" (
    echo ✓ PersonMenu.java найден
) else (
    echo ✗ PersonMenu.java не найден
)

if exist "src\main\java\gb\jdk_5\View\PersonView.java" (
    echo ✓ PersonView.java найден
) else (
    echo ✗ PersonView.java не найден
)

echo.
echo 2. Проверка pom.xml...
if exist "pom.xml" (
    echo ✓ pom.xml найден
    findstr "postgresql" pom.xml >nul
    if %errorlevel% equ 0 (
        echo ✓ PostgreSQL драйвер добавлен в зависимости
    ) else (
        echo ✗ PostgreSQL драйвер не найден в pom.xml
    )
) else (
    echo ✗ pom.xml не найден
)

echo.
echo 3. Проверка документации...
if exist "README.md" (
    echo ✓ README.md найден
) else (
    echo ✗ README.md не найден
)

if exist "DATABASE_SETUP.md" (
    echo ✓ DATABASE_SETUP.md найден
) else (
    echo ✗ DATABASE_SETUP.md не найден
)

echo.
echo 4. Проверка SQL скрипта...
if exist "src\main\java\gb\jdk_5\Database\phonebook_database.sql" (
    echo ✓ phonebook_database.sql найден
) else (
    echo ✗ phonebook_database.sql не найден
)

echo.
echo ========================================
echo Проверка завершена!
echo.
echo Для запуска проекта:
echo 1. Установите PostgreSQL
echo 2. Создайте базу данных (см. DATABASE_SETUP.md)
echo 3. Настройте подключение в PersonController.java
echo 4. Запустите через IDE или Maven
echo.
pause 