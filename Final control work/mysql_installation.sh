#!/bin/bash

# Задание 3: Работа с MySQL в Linux
echo "=== Установка MySQL ==="

# Обновление списка пакетов
sudo apt update

# Установка MySQL Server
sudo apt install mysql-server -y

# Запуск и включение MySQL
sudo systemctl start mysql
sudo systemctl enable mysql

# Настройка безопасности MySQL
sudo mysql_secure_installation

echo "MySQL установлен и настроен!"

# Задание 4: Управление deb-пакетами
echo "=== Управление deb-пакетами ==="

# Пример установки deb-пакета
echo "Установка примера deb-пакета..."
# sudo dpkg -i package.deb

# Пример удаления deb-пакета
echo "Удаление примера deb-пакета..."
# sudo dpkg -r package_name

echo "Управление пакетами завершено!"

# Задание 5: История команд в терминале Ubuntu
echo "=== Сохранение истории команд ==="

# Сохранение истории команд в файл
history > terminal_history.txt

echo "История команд сохранена в файл terminal_history.txt"
echo "Содержимое файла:"
cat terminal_history.txt 