# Makefile для системы учета питомника

# Переменные
JAVA_SRC = src/main/java
JAVA_FILES = $(wildcard $(JAVA_SRC)/*.java)
MAIN_CLASS = Main

# Компиляция Java файлов
compile:
	@echo "Компиляция Java файлов..."
	javac $(JAVA_FILES)
	@echo "Компиляция завершена!"

# Запуск программы
run: compile
	@echo "Запуск программы..."
	java -cp $(JAVA_SRC) $(MAIN_CLASS)

# Очистка скомпилированных файлов
clean:
	@echo "Очистка скомпилированных файлов..."
	rm -f $(JAVA_SRC)/*.class
	@echo "Очистка завершена!"

# Создание JAR файла
jar: compile
	@echo "Создание JAR файла..."
	cd $(JAVA_SRC) && jar cvfe ../../AnimalRegistry.jar $(MAIN_CLASS) *.class
	@echo "JAR файл создан: AnimalRegistry.jar"

# Запуск JAR файла
run-jar: jar
	@echo "Запуск JAR файла..."
	java -jar AnimalRegistry.jar

# Выполнение Linux команд
linux-setup:
	@echo "Выполнение Linux команд..."
	chmod +x linux_commands.sh
	./linux_commands.sh

# Установка MySQL
mysql-setup:
	@echo "Установка MySQL..."
	chmod +x mysql_installation.sh
	./mysql_installation.sh

# Помощь
help:
	@echo "Доступные команды:"
	@echo "  make compile    - Компиляция Java файлов"
	@echo "  make run        - Компиляция и запуск программы"
	@echo "  make clean      - Очистка скомпилированных файлов"
	@echo "  make jar        - Создание JAR файла"
	@echo "  make run-jar    - Запуск JAR файла"
	@echo "  make linux-setup - Выполнение Linux команд"
	@echo "  make mysql-setup - Установка MySQL"
	@echo "  make help       - Показать эту справку"

.PHONY: compile run clean jar run-jar linux-setup mysql-setup help 