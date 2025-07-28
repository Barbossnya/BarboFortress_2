#!/bin/bash

# Задание 1: Использование команды cat в Linux
echo "Создание файлов с животными..."

# Создание файла Pets.txt с домашними животными
cat > Pets.txt << EOF
Собаки
Кошки
Хомяки
EOF

# Создание файла PackAnimals.txt с вьючными животными
cat > PackAnimals.txt << EOF
Лошади
Верблюды
Ослы
EOF

echo "Файлы созданы:"
ls -la Pets.txt PackAnimals.txt

# Объединение файлов в HumanFriends.txt
echo "Объединение файлов..."
cat Pets.txt PackAnimals.txt > HumanFriends.txt

echo "Содержимое объединенного файла:"
cat HumanFriends.txt

echo "Файлы в директории:"
ls -la

# Задание 2: Работа с директориями в Linux
echo "Создание новой директории..."
mkdir AnimalFiles

echo "Перемещение файла HumanFriends.txt..."
mv HumanFriends.txt AnimalFiles/

echo "Содержимое директории AnimalFiles:"
ls -la AnimalFiles/

echo "Все файлы в текущей директории:"
ls -la 