package gb.intermediate_certification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.lang3.StringUtils;

/**
 * Тесты для демонстрации работы с Maven и сторонними библиотеками.
 */
class AppTest {

    @Test
    @DisplayName("Проверка, что приложение запускается без ошибок")
    void testAppLaunch() {
        // Проверяем, что main метод не выбрасывает исключений
        assertDoesNotThrow(() -> {
            App.main(new String[]{});
        });
    }

    @Test
    @DisplayName("Демонстрация тестирования с Apache Commons Lang")
    void testCommonsLangUsage() {
        // Тестируем функции из Apache Commons Lang
        String testString = "  Hello World!  ";
        
        assertEquals("!dlroW olleH  ", StringUtils.reverse(testString));
        assertEquals("Hello World!", StringUtils.trim(testString));
        assertFalse(StringUtils.isEmpty(testString));
        assertFalse(StringUtils.isBlank(testString));
        
        // Тестируем пустые строки
        assertTrue(StringUtils.isEmpty(""));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank("   "));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\n"})
    @DisplayName("Параметризованный тест для проверки пустых строк")
    void testBlankStrings(String input) {
        assertTrue(StringUtils.isBlank(input), 
            "Строка '" + input + "' должна быть пустой");
    }

    @Test
    @DisplayName("Проверка работы с null значениями")
    void testNullHandling() {
        // Apache Commons Lang безопасно обрабатывает null
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isBlank(null));
        assertEquals("", StringUtils.trim(null));
    }

    @Test
    @DisplayName("Демонстрация различных методов StringUtils")
    void testVariousStringUtilsMethods() {
        String text = "hello world";
        
        // Капитализация
        assertEquals("Hello World", StringUtils.capitalize(text));
        
        // Подсчет символов
        assertEquals(2, StringUtils.countMatches(text, "l"));
        
        // Проверка на пустоту
        assertFalse(StringUtils.isEmpty(text));
        assertFalse(StringUtils.isBlank(text));
        
        // Проверка на наличие только букв
        assertTrue(StringUtils.isAlphaSpace(text));
    }
}
