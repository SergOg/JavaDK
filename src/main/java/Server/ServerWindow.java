package Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    public static final int POS_X = 500;
    public static final int POS_Y = 550;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    private final TextArea4 textArea = new TextArea4();
    private JButton btnStart = new JButton("Start");
    private JButton btnStop = new JButton("Stop");
    private JTextArea log = new JTextArea();
    private boolean isServerWorking;

    ServerWindow(){
        isServerWorking = false;
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking){
                    isServerWorking = false;
                    System.out.println("Server stopped " + isServerWorking + "\n");
                    textArea.textArea("Server stopped " + isServerWorking + "\n");
                }else {
                    System.out.println("Server not started " + isServerWorking + "\n");
                    textArea.textArea("Server not started " + isServerWorking + "\n");
                }

            }
        });

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking){
                    isServerWorking = true;
                    System.out.println("Server started " + isServerWorking + "\n");
                    textArea.textArea("Server started " + isServerWorking + "\n");
                }else {
                    System.out.println("Server working " + isServerWorking + "\n");
                    textArea.textArea("Server working " + isServerWorking + "\n");
                }

            }
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");

        setAlwaysOnTop(true);
        setLayout(new GridLayout(1, 2));
        add(btnStart);
        add(btnStop);

        setVisible(true);
    }
}
