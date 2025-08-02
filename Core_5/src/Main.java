import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Core_5: Тонкости работы ===");
        
        // Демонстрация резервного копирования
        System.out.println("\n1. Создание резервной копии файлов:");
        demonstrateBackup();
        
        // Демонстрация работы с игровым полем
        System.out.println("\n2. Работа с игровым полем крестиков-ноликов:");
        demonstrateTicTacToe();
    }
    
    /**
     * Демонстрирует создание резервной копии файлов
     */
    public static void demonstrateBackup() {
        // Создаем тестовые файлы для демонстрации
        createTestFiles();
        
        // Используем BackupManager для создания резервной копии
        BackupManager.createBackupWithInfo(".", "./backup");
    }
    
    /**
     * Создает тестовые файлы для демонстрации резервного копирования
     */
    public static void createTestFiles() {
        try {
            // Создаем несколько тестовых файлов
            String[] testFiles = {
                "test1.txt",
                "test2.txt", 
                "data.dat"
            };
            
            for (String filename : testFiles) {
                try (PrintWriter writer = new PrintWriter(filename)) {
                    writer.println("Тестовый файл: " + filename);
                    writer.println("Создан: " + new java.util.Date());
                    writer.println("Содержимое для демонстрации резервного копирования");
                }
                System.out.println("Создан тестовый файл: " + filename);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при создании тестовых файлов: " + e.getMessage());
        }
    }
    
    /**
     * Демонстрирует работу с игровым полем крестиков-ноликов
     * и сохранение в файл в сжатом формате (3 байта)
     */
    public static void demonstrateTicTacToe() {
        // Создаем игровое поле с помощью класса TicTacToeField
        TicTacToeField field = new TicTacToeField();
        
        // Заполняем поле примерами (0-пусто, 1-крестик, 2-нолик, 3-резерв)
        field.setCell(0, 0, TicTacToeField.CROSS);  // крестик
        field.setCell(0, 1, TicTacToeField.EMPTY);  // пусто
        field.setCell(0, 2, TicTacToeField.ZERO);   // нолик
        field.setCell(1, 0, TicTacToeField.EMPTY);  // пусто
        field.setCell(1, 1, TicTacToeField.CROSS);  // крестик
        field.setCell(1, 2, TicTacToeField.EMPTY);  // пусто
        field.setCell(2, 0, TicTacToeField.ZERO);   // нолик
        field.setCell(2, 1, TicTacToeField.EMPTY);  // пусто
        field.setCell(2, 2, TicTacToeField.CROSS);  // крестик
        
        System.out.println("Исходное игровое поле:");
        field.printField();
        
        // Проверяем состояние игры
        int winner = field.checkWinner();
        if (winner != TicTacToeField.EMPTY) {
            System.out.println("Победитель: " + (winner == TicTacToeField.CROSS ? "Крестики" : "Нолики"));
        } else {
            System.out.println("Победителя пока нет");
        }
        
        System.out.println("Пустых ячеек: " + field.getEmptyCount());
        
        // Сохраняем в файл в сжатом формате (3 байта)
        try {
            field.saveToFile("game_field.dat");
            
            // Загружаем и восстанавливаем поле
            TicTacToeField loadedField = TicTacToeField.loadFromFile("game_field.dat");
            System.out.println("\nЗагруженное игровое поле:");
            loadedField.printField();
            
            // Проверяем, что данные восстановились корректно
            System.out.println("Проверка восстановления данных:");
            int[] original = field.getField();
            int[] loaded = loadedField.getField();
            boolean isCorrect = true;
            for (int i = 0; i < 9; i++) {
                if (original[i] != loaded[i]) {
                    isCorrect = false;
                    break;
                }
            }
            System.out.println("Данные восстановлены " + (isCorrect ? "корректно" : "с ошибками"));
            
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом: " + e.getMessage());
        }
    }
    

} 