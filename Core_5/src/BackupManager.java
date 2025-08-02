import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Класс для управления резервным копированием файлов
 */
public class BackupManager {
    
    /**
     * Создает резервную копию всех файлов в указанной директории
     * @param sourceDir исходная директория
     * @param backupDir директория для резервной копии
     * @return количество скопированных файлов
     */
    public static int createBackup(String sourceDir, String backupDir) throws IOException {
        Path sourcePath = Paths.get(sourceDir);
        Path backupPath = Paths.get(backupDir);
        
        // Создаем папку backup если её нет
        if (!Files.exists(backupPath)) {
            Files.createDirectory(backupPath);
        }
        
        // Получаем список всех файлов в исходной директории (без поддиректорий)
        List<Path> files = new ArrayList<>();
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(sourcePath)) {
            for (Path path : stream) {
                if (Files.isRegularFile(path)) {
                    files.add(path);
                }
            }
        }
        
        // Копируем файлы в backup
        int copiedCount = 0;
        for (Path file : files) {
            Path targetFile = backupPath.resolve(file.getFileName());
            Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
            copiedCount++;
        }
        
        return copiedCount;
    }
    
    /**
     * Создает резервную копию с дополнительной информацией
     */
    public static void createBackupWithInfo(String sourceDir, String backupDir) {
        try {
            System.out.println("Создание резервной копии...");
            System.out.println("Исходная директория: " + sourceDir);
            System.out.println("Директория резервной копии: " + backupDir);
            
            int copiedCount = createBackup(sourceDir, backupDir);
            
            System.out.println("Резервная копия создана успешно!");
            System.out.println("Скопировано файлов: " + copiedCount);
            
            // Показываем информацию о созданных файлах
            Path backupPath = Paths.get(backupDir);
            if (Files.exists(backupPath)) {
                System.out.println("\nСодержимое папки резервной копии:");
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(backupPath)) {
                    for (Path path : stream) {
                        if (Files.isRegularFile(path)) {
                            long size = Files.size(path);
                            System.out.println("  " + path.getFileName() + " (" + size + " байт)");
                        }
                    }
                }
            }
            
        } catch (IOException e) {
            System.err.println("Ошибка при создании резервной копии: " + e.getMessage());
        }
    }
} 