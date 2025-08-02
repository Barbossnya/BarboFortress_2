# Используем официальный образ OpenJDK
FROM openjdk:11-jdk

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем исходный код
COPY src/ ./src/

# Копируем все файлы проекта
COPY *.md ./
COPY *.dat ./
COPY *.txt ./
COPY *.bat ./
COPY *.ps1 ./

# Компилируем Java приложение
RUN javac src/*.java

# Создаем папку для резервных копий
RUN mkdir -p backup

# Открываем порт (если понадобится в будущем)
EXPOSE 8080

# Команда по умолчанию - запуск приложения
CMD ["java", "-cp", "src", "Main"] 