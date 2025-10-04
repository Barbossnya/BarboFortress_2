package com.downloader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class MultiThreadDownloader {
    private final int threadCount;
    private final ExecutorService executorService;
    private final AtomicLong downloadedBytes = new AtomicLong(0);
    private final AtomicLong totalBytes = new AtomicLong(0);

    public MultiThreadDownloader(int threadCount) {
        this.threadCount = threadCount;
        this.executorService = Executors.newFixedThreadPool(threadCount);
    }

    public void downloadFile(String fileUrl, String outputPath) throws Exception {
        URL url = new URL(fileUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        
        long fileSize = connection.getContentLengthLong();
        if (fileSize <= 0) {
            throw new RuntimeException("Не удалось определить размер файла");
        }
        
        totalBytes.set(fileSize);
        downloadedBytes.set(0);
        
        System.out.println("Начинаем загрузку файла: " + fileUrl);
        System.out.println("Размер файла: " + formatFileSize(fileSize));
        System.out.println("Количество потоков: " + threadCount);
        
        // Создаем временные файлы для каждого потока
        File[] tempFiles = new File[threadCount];
        for (int i = 0; i < threadCount; i++) {
            tempFiles[i] = File.createTempFile("download_part_" + i, ".tmp");
        }
        
        // Вычисляем размер части для каждого потока
        long partSize = fileSize / threadCount;
        long remainingBytes = fileSize % threadCount;
        
        // Создаем задачи для каждого потока
        Future<?>[] futures = new Future[threadCount];
        for (int i = 0; i < threadCount; i++) {
            long startByte = i * partSize;
            long endByte = (i == threadCount - 1) ? 
                startByte + partSize + remainingBytes - 1 : 
                startByte + partSize - 1;
            
            DownloadTask task = new DownloadTask(fileUrl, tempFiles[i], startByte, endByte, i);
            futures[i] = executorService.submit(task);
        }
        
        // Ждем завершения всех потоков
        for (Future<?> future : futures) {
            future.get();
        }
        
        // Объединяем временные файлы в один
        mergeFiles(tempFiles, outputPath);
        
        // Удаляем временные файлы
        for (File tempFile : tempFiles) {
            tempFile.delete();
        }
        
        System.out.println("Загрузка завершена: " + outputPath);
    }
    
    private void mergeFiles(File[] tempFiles, String outputPath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(outputPath)) {
            for (File tempFile : tempFiles) {
                try (FileInputStream inputStream = new FileInputStream(tempFile)) {
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        }
    }
    
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
    
    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.1f MB", bytes / (1024.0 * 1024.0));
        return String.format("%.1f GB", bytes / (1024.0 * 1024.0 * 1024.0));
    }
    
    public double getProgress() {
        long total = totalBytes.get();
        if (total == 0) return 0.0;
        return (double) downloadedBytes.get() / total * 100.0;
    }
    
    private class DownloadTask implements Runnable {
        private final String fileUrl;
        private final File outputFile;
        private final long startByte;
        private final long endByte;
        private final int threadId;
        
        public DownloadTask(String fileUrl, File outputFile, long startByte, long endByte, int threadId) {
            this.fileUrl = fileUrl;
            this.outputFile = outputFile;
            this.startByte = startByte;
            this.endByte = endByte;
            this.threadId = threadId;
        }
        
        @Override
        public void run() {
            try {
                URL url = new URL(fileUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Range", "bytes=" + startByte + "-" + endByte);
                
                try (InputStream inputStream = connection.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(outputFile)) {
                    
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    long downloadedInThread = 0;
                    
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        downloadedInThread += bytesRead;
                        downloadedBytes.addAndGet(bytesRead);
                        
                        // Показываем прогресс каждые 100KB
                        if (downloadedInThread % (100 * 1024) == 0) {
                            System.out.printf("Поток %d: %s загружено, общий прогресс: %.1f%%%n", 
                                threadId, formatFileSize(downloadedInThread), getProgress());
                        }
                    }
                }
                
                System.out.printf("Поток %d завершен. Загружено: %s%n", 
                    threadId, formatFileSize(endByte - startByte + 1));
                    
            } catch (Exception e) {
                System.err.println("Ошибка в потоке " + threadId + ": " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
} 