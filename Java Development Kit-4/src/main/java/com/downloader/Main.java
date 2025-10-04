package com.downloader;

/* Урок 4. Коллекции
Создайте приложение, которое скачивает файлы из интернета в несколько потоков.
У пользователя должна быть возможность указать, сколько потоков использовать для загрузки.
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Многопоточный загрузчик файлов ===");
        System.out.println();

        try {
            // Запрашиваем URL файла
            System.out.print("Введите URL файла для загрузки: ");
            String fileUrl = scanner.nextLine().trim();

            if (fileUrl.isEmpty()) {
                System.out.println("URL не может быть пустым!");
                return;
            }

            // Проверяем, что URL начинается с http:// или https://
            if (!fileUrl.startsWith("http://") && !fileUrl.startsWith("https://")) {
                System.out.println("URL должен начинаться с http:// или https://");
                return;
            }

            // Запрашиваем количество потоков
            System.out.print("Введите количество потоков для загрузки (1-10): ");
            int threadCount;
            try {
                threadCount = Integer.parseInt(scanner.nextLine().trim());
                if (threadCount < 1 || threadCount > 10) {
                    System.out.println("Количество потоков должно быть от 1 до 10. Используем 4 потока.");
                    threadCount = 4;
                }
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Используем 4 потока.");
                threadCount = 4;
            }

            // Запрашиваем путь для сохранения
            System.out.print("Введите путь для сохранения файла (или нажмите Enter для текущей папки): ");
            String outputPath = scanner.nextLine().trim();

            if (outputPath.isEmpty()) {
                // Извлекаем имя файла из URL
                String fileName = getFileNameFromUrl(fileUrl);
                outputPath = fileName;
            }

            System.out.println();
            System.out.println("Начинаем загрузку...");
            System.out.println("URL: " + fileUrl);
            System.out.println("Потоков: " + threadCount);
            System.out.println("Путь сохранения: " + outputPath);
            System.out.println();

            // Создаем загрузчик и начинаем загрузку
            SimpleDownloader downloader = new SimpleDownloader(threadCount);

            try {
                downloader.downloadFile(fileUrl, outputPath);
                System.out.println();
                System.out.println("✅ Загрузка успешно завершена!");
                System.out.println("Файл сохранен: " + outputPath);
            } catch (Exception e) {
                System.err.println("❌ Ошибка при загрузке: " + e.getMessage());
                e.printStackTrace();
            } finally {
                downloader.shutdown();
            }

        } catch (Exception e) {
            System.err.println("❌ Произошла ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static String getFileNameFromUrl(String url) {
        try {
            // Извлекаем имя файла из URL
            String fileName = url.substring(url.lastIndexOf('/') + 1);
            if (fileName.isEmpty() || !fileName.contains(".")) {
                fileName = "downloaded_file";
            }
            return fileName;
        } catch (Exception e) {
            return "downloaded_file";
        }
    }
}