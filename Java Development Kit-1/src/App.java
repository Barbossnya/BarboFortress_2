

import Server.ClientGUI;
import Server.ServerWindow;

public class App {
    public static void main(String[] args) {
        //Запуск окна игры
        new GameWindow();

        // Запуск окна сервера
        new ServerWindow();

        // Запуск клиента чата
        new ClientGUI();
    }
}
