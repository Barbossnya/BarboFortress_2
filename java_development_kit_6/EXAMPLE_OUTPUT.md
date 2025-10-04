# Пример вывода программы

После запуска `java -jar target/java_development_kit_6-1.0.0.jar` вы увидите:

```
14:30:15.123 [main] INFO  gb.intermediate_certification.App - Запуск приложения с использованием Maven и сторонних библиотек
14:30:15.125 [main] INFO  gb.intermediate_certification.App - === Демонстрация Apache Commons Lang ===
Исходный текст: '  Hello World!  '
Перевернутый текст: '!dlroW olleH  '
Обрезанный текст: 'Hello World!'
Пустая строка? false
Пустая или только пробелы? false

14:30:15.127 [main] INFO  gb.intermediate_certification.App - === Демонстрация логирования ===
14:30:15.127 [main] DEBUG gb.intermediate_certification.App - Это отладочное сообщение
14:30:15.127 [main] INFO  gb.intermediate_certification.App - Это информационное сообщение
14:30:15.127 [main] WARN  gb.intermediate_certification.App - Это предупреждение
14:30:15.127 [main] ERROR gb.intermediate_certification.App - Это сообщение об ошибке
Логи записаны в файл и консоль

14:30:15.129 [main] INFO  gb.intermediate_certification.App - === Демонстрация JSON обработки ===
Созданный JSON:
{"name":"Java Development Kit 6","version":"1.0.0","description":"Проект для изучения Maven","dependencies":["Apache Commons Lang","SLF4J","Jackson"]}

Парсинг JSON:
Название: Java Development Kit 6
Версия: 1.0.0
Описание: Проект для изучения Maven
Зависимости:
  - Apache Commons Lang
  - SLF4J
  - Jackson

14:30:15.131 [main] INFO  gb.intermediate_certification.App - Приложение завершено успешно
```

## Что демонстрирует этот вывод:

1. **Логирование**: Разные уровни логов (DEBUG, INFO, WARN, ERROR) с временными метками
2. **Apache Commons Lang**: Работа со строками (reverse, trim, isEmpty, isBlank)
3. **JSON обработка**: Создание и парсинг JSON с помощью Jackson
4. **Структурированный вывод**: Четкое разделение демонстраций

## Файлы логов

Логи также сохраняются в файл `logs/application.log` с ротацией по дням. 