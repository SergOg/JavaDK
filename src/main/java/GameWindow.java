import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    public static final int WINDOW_HEIGHT = 555;
    public static final int WINDOW_WIDTH = 507;

    JButton btnStart = new JButton("New Game");
    JButton btnExit = new JButton("Exit");
    Map map;

    GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTakToe");
        setResizable(false);
        setLocationRelativeTo(null);

        SettingsWindow settingsWindow = new SettingsWindow(this);//
        map = new Map();

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }//
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow.setVisible(true);
            }//
        });

        JPanel panButton = new JPanel(new GridLayout(1, 2));
        panButton.add(btnStart);
        panButton.add(btnExit);
        add(panButton, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }
    void startNewGame( int mode, int fSzX, int fSzY, int wLen){
        map.startNewGame(mode, fSzX, fSzY, wLen);
    }
}
