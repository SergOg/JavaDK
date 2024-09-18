package Server;

import javax.swing.*;
import java.awt.*;

public class TextArea4 extends JFrame {

    public TextArea4() {
        super("Вывод в JTextArea");
    }

    public void textArea(String s) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JTextArea area = new JTextArea(11, 30);
        area.setFont(new Font("Dialog", Font.PLAIN, 14));
        area.setText(s);
        // Параметры переноса слов
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        // Добавим поля в окно
        JPanel contents = new JPanel();
        contents.add(new JScrollPane(area));
        setContentPane(contents);

        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }
}
