## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

/* 
 * Задание 2
 * Задача: Добавить компонентам 
интерактивности, а именно, при перемещении 
ползунка слайдера в соответствующих лейблах 
должны появляться текущие значения 
слайдеров. Для этого необходимо добавить 
к слайдеру слушателя изменений (как это было 
сделано для действия кнопки). 
 *         JLabel lbFieldSize = new JLabel(FIELD_SIZE_PREFIX + MIN_FIELD_SIZE);
        JLabel lbWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_FIELD_SIZE);
        slideFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        slideWinLen = new JSlider(MIN_WIN_LENGTH, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        slideWinLen.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lbWinLength.setText(WIN_LENGTH_PREFIX + slideWinLen.getValue());
            }
        });
        slideFieldSize.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                int currentValue = slideFieldSize.getValue();
                lbFieldSize.setText(FIELD_SIZE_PREFIX + currentValue);
                slideWinLen.setMaximum(currentValue);
            }
        });
 */

/* 
 * Задание 3
Задача: В методе обработчика нажатия 
кнопки необходимо заменить константы 
в аргументе вызова метода старта игры 
на текущие показания компонентов 
(какая радио-кнопка активна, значение 
слайдера размеров поля, значение 
слайдера выигрышной длины). 
 * public static final int MODE_HVA = 0;
    public static final int MODE_HVH = 1;

    private void btnStartDelegate(){
        int gameMode;
        if(humVSAI.isSelected()){
            gameMode = Map.MODE_HVA;
        } else if (humVShum.isSelected()){
            gameMode = Map.MODE_HVH;
        } else {
            throw new RuntimeException("Unknown game mode");
        }
        gameWindow.startNewGame(gameMode, fieldSizeX, fieldSizeY, winLen);
        setVisible(false);
    }

 */


/*
 * Задание 4
 * Задача: Создать простейшее окно управления сервером 
(по сути, любым), содержащее две кнопки (JButton) – 
запустить сервер и остановить сервер. Кнопки должны 
просто логировать нажатие (имитировать запуск и остановку 
сервера, соответственно) и выставлять внутри интерфейса 
соответствующее булево isServerWorking.  

 * private ServerWindow(){
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                isServerWorking = false;
                System.out.println("Server stopped " + isServerWorking + "\n");
            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                isServerWorking = true;
                System.out.println("Server started " + isServerWorking + "\n");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        setLayout(new GridLayout(1, 2));
        add(btnStart);
        add(btnStop);

        setVisible(true);
 */

/*
 *  Задание 5  
 * Задача: Создать окно клиента чата. Окно должно содержать 
JtextField для ввода логина, пароля, IP-адреса сервера, порта 
подключения к серверу, область ввода сообщений, JTextArea 
область просмотра сообщений чата и JButton подключения 
к серверу и отправки сообщения в чат. Желательно сразу 
сгруппировать компоненты, относящиеся к серверу 
сгруппировать на JPanel сверху экрана, а компоненты, 
относящиеся к отправке сообщения – на JPanel снизу
 * private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();

    private final JPanel panelTop = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JTextField tfLogin = new JTextField("ivan_igorevich");
    private final JPasswordField tfPassword = new JPasswordField("123456");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    ClientGUI(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        add(scrollLog);

        setVisible(true);
    }
 */
