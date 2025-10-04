package Server;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private final JTextArea log = new JTextArea();
    private final JTextField tfMessage = new JTextField();
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("ivan_igorevich");
    private final JPasswordField tfPassword = new JPasswordField("123456");

    public ClientGUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 300);
        setTitle("Чат клиент");

        JPanel panelTop = new JPanel(new GridLayout(2, 3));
        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(new JButton("Login"));

        JPanel panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        JButton btnSend = new JButton("Отправить");
        panelBottom.add(btnSend, BorderLayout.EAST);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = tfMessage.getText();
                log.append(message + "\n");
                tfMessage.setText("");
            }
        });

        tfMessage.addActionListener(e -> btnSend.doClick());
    }

    public static void main(String[] args) {
        new ClientGUI();
    }
}