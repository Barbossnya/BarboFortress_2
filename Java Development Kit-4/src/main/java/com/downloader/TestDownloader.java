package com.downloader;

public class TestDownloader {
    public static void main(String[] args) {
        // Тестируем с простым файлом
        String testUrl = "https://httpbin.org/bytes/1024"; // Простой тестовый файл 1KB
        String outputPath = "test_file.bin";
        int threadCount = 2;
        
        System.out.println("=== Тест многопоточного загрузчика ===");
        System.out.println("URL: " + testUrl);
        System.out.println("Потоков: " + threadCount);
        System.out.println("Выходной файл: " + outputPath);
        System.out.println();
        
        SimpleDownloader downloader = new SimpleDownloader(threadCount);
        
        try {
            downloader.downloadFile(testUrl, outputPath);
            System.out.println();
            System.out.println("✅ Тестовая загрузка завершена успешно!");
            System.out.println("Файл сохранен: " + outputPath);
        } catch (Exception e) {
            System.err.println("❌ Ошибка при тестовой загрузке: " + e.getMessage());
            e.printStackTrace();
        } finally {
            downloader.shutdown();
        }
    }
} 