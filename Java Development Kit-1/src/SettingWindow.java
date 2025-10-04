import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 280;
    private static final int WINDOW_WIDTH = 400;
    private static final String FIELD_SIZE_PREFIX = "Размер поля: ";
    private static final String WIN_LENGTH_PREFIX = "Длина для победы: ";
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;
    private static final int MIN_WIN_LENGTH = 3;

    JButton btnStart = new JButton("Start new game");
    private JSlider slideFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
    private JSlider slideWinLen = new JSlider(MIN_WIN_LENGTH, MAX_FIELD_SIZE, MIN_WIN_LENGTH);
    private JLabel lbFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
    private JLabel lbWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_WIN_LENGTH);
    
    // Добавляем поля для радио-кнопок
    private JRadioButton btn1;
    private JRadioButton btn2;
    private GameWindow gameWindow;

    public SettingWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setLocationRelativeTo(gameWindow);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Добавление слушателя на кнопку
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartDelegate(); // Вызов метода для обработки нажатия кнопки
            }
        });

        JPanel settings = new JPanel(new GridLayout(5, 1));

        // Создание панели выбора режима игры
        JPanel typeGame = new JPanel(new GridLayout(3, 1));
        typeGame.add(new JLabel("Выберите режим игры"));
        ButtonGroup group1 = new ButtonGroup();
        btn1 = new JRadioButton("Человек против компьютера");
        btn1.setSelected(true);
        btn2 = new JRadioButton("Человек против человека");
        group1.add(btn1);
        group1.add(btn2);
        typeGame.add(btn1);
        typeGame.add(btn2);

        // Панель настроек поля
        JPanel sizeField = new JPanel(new GridLayout(3, 1));
        sizeField.add(new JLabel("Размер поля:"));
        sizeField.add(slideFieldSize);
        sizeField.add(lbFieldSize);

        // Панель настроек длины победы
        JPanel sizeWin = new JPanel(new GridLayout(3, 1));
        sizeWin.add(new JLabel("Длина для победы:"));
        sizeWin.add(slideWinLen);
        sizeWin.add(lbWinLength);

        settings.add(typeGame);
        settings.add(sizeField);
        settings.add(sizeWin);
        add(settings);
        add(btnStart, BorderLayout.SOUTH);

        // Слушатели изменения слайдеров
        slideFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = slideFieldSize.getValue();
                lbFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
                slideWinLen.setMaximum(currentValue);
            }
        });

        slideWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LENGTH_PREFIX + slideWinLen.getValue());
            }
        });
    }

    private void btnStartDelegate() {
        int gameMode;
        if (btn1.isSelected()) {
            gameMode = Map.MODE_HVA;
        } else if (btn2.isSelected()) {
            gameMode = Map.MODE_HVH;
        } else {
            throw new RuntimeException("Unknown game mode");
        }

        // Получение значений из слайдеров
        int fieldSizeX = slideFieldSize.getValue();
        int fieldSizeY = fieldSizeX;
        int winLen = slideWinLen.getValue();

        gameWindow.startNewGame(gameMode, fieldSizeX, fieldSizeY, winLen);
        setVisible(false);
    }
}