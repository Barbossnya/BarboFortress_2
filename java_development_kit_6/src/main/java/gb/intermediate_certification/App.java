package gb.intermediate_certification;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

/* Урок 6. Управление проектом: сборщики проектов
Настройте проект с использованием сборщика проектов Maven или Gradle. 
В проекте должны быть зависимости от сторонних библиотек, 
а также минимальные настройки сборки (например, указание версии Java, настройка плагинов).
 */
public final class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    
    private App() {
    }

    /**
     * Демонстрирует использование сторонних библиотек в Maven проекте.
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        logger.info("Запуск приложения с использованием Maven и сторонних библиотек");
        
        // Демонстрация Apache Commons Lang
        demonstrateCommonsLang();
        
        // Демонстрация логирования
        demonstrateLogging();
        
        // Демонстрация JSON обработки
        demonstrateJsonProcessing();
        
        logger.info("Приложение завершено успешно");
    }
    
    /**
     * Демонстрирует использование Apache Commons Lang.
     */
    private static void demonstrateCommonsLang() {
        logger.info("=== Демонстрация Apache Commons Lang ===");
        
        String text = "  Hello World!  ";
        String reversed = StringUtils.reverse(text);
        String trimmed = StringUtils.trim(text);
        boolean isEmpty = StringUtils.isEmpty(text);
        boolean isBlank = StringUtils.isBlank(text);
        
        System.out.println("Исходный текст: '" + text + "'");
        System.out.println("Перевернутый текст: '" + reversed + "'");
        System.out.println("Обрезанный текст: '" + trimmed + "'");
        System.out.println("Пустая строка? " + isEmpty);
        System.out.println("Пустая или только пробелы? " + isBlank);
        System.out.println();
    }
    
    /**
     * Демонстрирует различные уровни логирования.
     */
    private static void demonstrateLogging() {
        logger.info("=== Демонстрация логирования ===");
        
        logger.debug("Это отладочное сообщение");
        logger.info("Это информационное сообщение");
        logger.warn("Это предупреждение");
        logger.error("Это сообщение об ошибке");
        
        System.out.println("Логи записаны в файл и консоль");
        System.out.println();
    }
    
    /**
     * Демонстрирует обработку JSON с помощью Jackson.
     */
    private static void demonstrateJsonProcessing() {
        logger.info("=== Демонстрация JSON обработки ===");
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            
            // Создание JSON из Map
            Map<String, Object> data = new HashMap<>();
            data.put("name", "Java Development Kit 6");
            data.put("version", "1.0.0");
            data.put("description", "Проект для изучения Maven");
            data.put("dependencies", new String[]{"Apache Commons Lang", "SLF4J", "Jackson"});
            
            String json = mapper.writeValueAsString(data);
            System.out.println("Созданный JSON:");
            System.out.println(json);
            System.out.println();
            
            // Парсинг JSON обратно в объект
            JsonNode jsonNode = mapper.readTree(json);
            System.out.println("Парсинг JSON:");
            System.out.println("Название: " + jsonNode.get("name").asText());
            System.out.println("Версия: " + jsonNode.get("version").asText());
            System.out.println("Описание: " + jsonNode.get("description").asText());
            System.out.println("Зависимости:");
            jsonNode.get("dependencies").forEach(dep -> 
                System.out.println("  - " + dep.asText()));
            
        } catch (Exception e) {
            logger.error("Ошибка при обработке JSON", e);
        }
    }
}
