package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClientGUI extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private JTextArea log = new JTextArea();
    private static final String LOG_FILE = "chat_log.txt";

    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private JTextField tfIPAddress = new JTextField("127.0.0.1");
    private JTextField tfPort = new JTextField("8189");
    private JTextField tfLogin = new JTextField("ivan igorevich");
    private final JPasswordField tfPassword = new JPasswordField("123456");
    private JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private JTextField tfMessage = new JTextField();
    private JButton btnSend = new JButton("Send");

    public ClientGUI(ServerWindow serverWindow) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");

        log.setBackground(Color.GRAY);
        loadChatHistory();

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        add(panelTop, BorderLayout.NORTH);

        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);
        add(panelBottom, BorderLayout.SOUTH);

        log.setEnabled(false);

        JScrollPane sckrollLog = new JScrollPane(log);
        add(sckrollLog);

        btnSend.addActionListener(e -> sendMessage(tfLogin));
        tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage(tfLogin);
                }
            }
        });

        setVisible(true);
    }

    private void sendMessage(JTextField login) {
        String message = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss")) + " " + login + " : " + tfMessage.getText() + "\n";
        log.append(message);
        saveMessageToFile(message);
        tfMessage.setText("");
    }

    private void saveMessageToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadChatHistory() {
        File file = new File(LOG_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    log.append(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
