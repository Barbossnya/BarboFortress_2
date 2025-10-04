package gb.jdk_5;

import gb.jdk_5.View.PhonebookGUI;
import gb.jdk_5.View.PhonebookGUIMock;
import javafx.application.Application;

/* Урок 5. Многопоточность
Реализуйте простой графический интерфейс для вашего телефонного справочника 
из предыдущего задания с использованием JavaFX или Swing.
 */

public class App {
    public static void main(String[] args) throws Exception {
        // Выберите версию для запуска:
        
        // 1. Версия с базой данных PostgreSQL (требует настройки БД)
        Application.launch(PhonebookGUI.class, args);
        
        // 2. Mock версия без базы данных (для тестирования)
        // Application.launch(PhonebookGUIMock.class, args);
    }
} 