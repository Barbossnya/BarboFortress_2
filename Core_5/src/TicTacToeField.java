import java.io.*;

/**
 * Класс для работы с игровым полем крестиков-ноликов
 * Поддерживает сжатое хранение в 3 байта
 */
public class TicTacToeField {
    
    public static final int EMPTY = 0;      // Пустое поле
    public static final int CROSS = 1;      // Крестик
    public static final int ZERO = 2;       // Нолик
    public static final int RESERVED = 3;   // Резервное значение
    
    private int[] field;
    
    /**
     * Создает пустое игровое поле
     */
    public TicTacToeField() {
        field = new int[9];
    }
    
    /**
     * Создает игровое поле с заданными значениями
     */
    public TicTacToeField(int[] values) {
        if (values.length != 9) {
            throw new IllegalArgumentException("Поле должно содержать ровно 9 элементов");
        }
        field = values.clone();
    }
    
    /**
     * Устанавливает значение в ячейку
     */
    public void setCell(int row, int col, int value) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            throw new IllegalArgumentException("Координаты должны быть от 0 до 2");
        }
        if (value < 0 || value > 3) {
            throw new IllegalArgumentException("Значение должно быть от 0 до 3");
        }
        field[row * 3 + col] = value;
    }
    
    /**
     * Получает значение из ячейки
     */
    public int getCell(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            throw new IllegalArgumentException("Координаты должны быть от 0 до 2");
        }
        return field[row * 3 + col];
    }
    
    /**
     * Возвращает массив значений поля
     */
    public int[] getField() {
        return field.clone();
    }
    
    /**
     * Выводит игровое поле в консоль
     */
    public void printField() {
        System.out.println("Игровое поле:");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                int value = getCell(i, j);
                switch (value) {
                    case EMPTY: System.out.print(" "); break;
                    case CROSS: System.out.print("X"); break;
                    case ZERO: System.out.print("O"); break;
                    case RESERVED: System.out.print("?"); break;
                    default: System.out.print("?"); break;
                }
                System.out.print(" | ");
            }
            System.out.println();
            if (i < 2) System.out.println("-------------");
        }
    }
    
    /**
     * Сохраняет поле в файл в сжатом формате (3 байта)
     * Каждое значение занимает 2 бита, всего 9 значений = 18 бит = 3 байта
     */
    public void saveToFile(String filename) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            // Упаковываем 9 значений по 2 бита каждое в 3 байта
            byte[] compressed = new byte[3];
            
            for (int i = 0; i < 9; i++) {
                int value = field[i] & 0x03; // Берем только 2 младших бита
                int byteIndex = i / 4; // В каком байте
                int bitOffset = (i % 4) * 2; // Смещение в битах
                
                // Устанавливаем 2 бита в нужной позиции
                compressed[byteIndex] |= (value << bitOffset);
            }
            
            fos.write(compressed);
            System.out.println("Поле сохранено в файл " + filename + " (3 байта)");
        }
    }
    
    /**
     * Загружает поле из файла в сжатом формате
     */
    public static TicTacToeField loadFromFile(String filename) throws IOException {
        int[] field = new int[9];
        
        try (FileInputStream fis = new FileInputStream(filename)) {
            byte[] compressed = new byte[3];
            int bytesRead = fis.read(compressed);
            
            if (bytesRead != 3) {
                throw new IOException("Неверный формат файла");
            }
            
            // Распаковываем 9 значений из 3 байт
            for (int i = 0; i < 9; i++) {
                int byteIndex = i / 4; // В каком байте
                int bitOffset = (i % 4) * 2; // Смещение в битах
                
                // Извлекаем 2 бита из нужной позиции
                int value = (compressed[byteIndex] >> bitOffset) & 0x03;
                field[i] = value;
            }
            
            System.out.println("Поле загружено из файла " + filename);
        }
        
        return new TicTacToeField(field);
    }
    
    /**
     * Проверяет, есть ли победитель
     */
    public int checkWinner() {
        // Проверяем горизонтали
        for (int i = 0; i < 3; i++) {
            if (getCell(i, 0) != EMPTY && 
                getCell(i, 0) == getCell(i, 1) && 
                getCell(i, 1) == getCell(i, 2)) {
                return getCell(i, 0);
            }
        }
        
        // Проверяем вертикали
        for (int j = 0; j < 3; j++) {
            if (getCell(0, j) != EMPTY && 
                getCell(0, j) == getCell(1, j) && 
                getCell(1, j) == getCell(2, j)) {
                return getCell(0, j);
            }
        }
        
        // Проверяем диагонали
        if (getCell(0, 0) != EMPTY && 
            getCell(0, 0) == getCell(1, 1) && 
            getCell(1, 1) == getCell(2, 2)) {
            return getCell(0, 0);
        }
        
        if (getCell(0, 2) != EMPTY && 
            getCell(0, 2) == getCell(1, 1) && 
            getCell(1, 1) == getCell(2, 0)) {
            return getCell(0, 2);
        }
        
        return EMPTY; // Нет победителя
    }
    
    /**
     * Проверяет, заполнено ли поле
     */
    public boolean isFull() {
        for (int value : field) {
            if (value == EMPTY) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Возвращает количество пустых ячеек
     */
    public int getEmptyCount() {
        int count = 0;
        for (int value : field) {
            if (value == EMPTY) {
                count++;
            }
        }
        return count;
    }
} 