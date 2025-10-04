package Server;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private boolean isServerWorking;

    public ServerWindow() {
        isServerWorking = false;
        JButton btnStart = new JButton("Запустить сервер");
        JButton btnStop = new JButton("Остановить сервер");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = true;
                System.out.println("Сервер запущен " + isServerWorking + "\n");
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isServerWorking = false;
                System.out.println("Сервер остановлен " + isServerWorking + "\n");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 100);
        setResizable(false);
        setTitle("Серверный интерфейс");
        setLayout(new GridLayout(1, 2));
        add(btnStart);
        add(btnStop);
        setVisible(true);
    }
}
