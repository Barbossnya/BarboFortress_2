package com.downloader;

public class Demo {
    public static void main(String[] args) {
        // Демонстрация работы загрузчика с тестовым файлом
        String testUrl = "https://speed.hetzner.de/100MB.bin"; // Тестовый файл 100MB
        String outputPath = "test_download.bin";
        int threadCount = 4;
        
        System.out.println("=== Демонстрация многопоточного загрузчика ===");
        System.out.println("Тестовый URL: " + testUrl);
        System.out.println("Количество потоков: " + threadCount);
        System.out.println("Выходной файл: " + outputPath);
        System.out.println();
        
        MultiThreadDownloader downloader = new MultiThreadDownloader(threadCount);
        
        try {
            downloader.downloadFile(testUrl, outputPath);
            System.out.println();
            System.out.println("✅ Демонстрационная загрузка завершена успешно!");
            System.out.println("Файл сохранен: " + outputPath);
        } catch (Exception e) {
            System.err.println("❌ Ошибка при демонстрационной загрузке: " + e.getMessage());
            e.printStackTrace();
        } finally {
            downloader.shutdown();
        }
    }
} 