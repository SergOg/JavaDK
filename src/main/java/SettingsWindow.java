import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsWindow extends JFrame {
    public static final int WINDOW_HEIGHT = 350;
    public static final int WINDOW_WIDTH = 300;
    private static final String SET_FIELD_SIZE = "Выберите размер поля: ";
    private static final String SET_WIN_SIZE = "Выберите длину для победы: ";

    JButton btnStart = new JButton("Start new game");
    JSlider sizeW = new JSlider(3, 10);
    JSlider sizeF = new JSlider(3, 10);
    JRadioButton pvc = new JRadioButton("Человек против компьютера");
    JRadioButton pvp = new JRadioButton("Человек против человека");
    GameWindow gameWindow;

    SettingsWindow(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(gameWindow);

        //Добавляем блок настроек и тип игры
        JPanel settings = new JPanel(new GridLayout(3, 1));
        JPanel typeGame = new JPanel(new GridLayout(3, 1));

        //setLayout(new GridLayout(10,1));
        typeGame.add(new JLabel("Выберите режим игры:"));
        ButtonGroup bgr = new ButtonGroup();//режим
        pvc.setSelected(true);

        bgr.add(pvc);
        bgr.add(pvp);
        typeGame.add(pvc);
        typeGame.add(pvp);

        //Выбор повторений для победы
        JPanel sizeWin = new JPanel(new GridLayout(3, 1));
        sizeWin.add(new JLabel("Выберите длину поля победы:"));
        JLabel lebInsLength = new JLabel(SET_WIN_SIZE);
        sizeWin.add(lebInsLength);

        sizeW.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int len = sizeW.getValue();
                lebInsLength.setText(SET_WIN_SIZE + len);
            }
        });
        sizeWin.add(sizeW);

        //Выбор размеров поля
        JPanel sizeField = new JPanel(new GridLayout(3, 1));
        sizeField.add(new JLabel("Выберите размер поля:"));
        JLabel lebCurrentSize = new JLabel(SET_FIELD_SIZE);
        sizeField.add(lebCurrentSize);

        sizeF.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int size = sizeF.getValue();
                lebCurrentSize.setText(SET_FIELD_SIZE + size);
                sizeW.setMaximum(size);
            }
        });
        sizeField.add(sizeF);

        //add(new JSlider(3,10,3));
        //Заполнение окна настроек
        settings.add(typeGame);
        settings.add(sizeField);
        settings.add(sizeWin);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        //Добавляе панель с настройками на основное окно
        add(settings);
        //Выводим кнопку начала игры
        add(btnStart, BorderLayout.SOUTH);
    }

    private void startNewGame() {
        int mode = 0;
        if (pvc.isSelected()){
            mode = 1;
        }else  if (pvp.isSelected()){
            mode = 2;
        }
        int sizeField = sizeF.getValue();
        int sizeWin = sizeW.getValue();
        gameWindow.startNewGame(mode, sizeField, sizeField, sizeWin);
        setVisible(false);
    }
}
