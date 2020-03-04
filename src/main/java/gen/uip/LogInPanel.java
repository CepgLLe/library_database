package gen.uip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * <title> Панель для ввода пароля. </title>
 * <strong> Внедрить паттерн Singleton </strong>
 */

public class LogInPanel extends JPanel {

    private static LogInPanel instance;

    private final int HEIGHT      = 25;
    private final int WIDTH_LABEL = 80;
    private final int WIDTH_TF    = 150;
    private final int X_LABEL     = 160;
    private final int X_TF        = 217;

    private JLabel logInLabel;
    private JTextField logInTF;

    private JLabel passwordLabel;
    private JPasswordField passwordPF;

    private JButton logInButton;

    private LogInPanel() {
        setBackground(Color.LIGHT_GRAY);
        setSize(584,211); // Внутри рамки: width окна -16, height окна -39
        setLayout(null);

        logInLabel    = new JLabel("Логин:");
        logInTF       = new JTextField();

        passwordLabel = new JLabel("Пароль:");
        passwordPF    = new JPasswordField();

        logInButton   = new JButton("Войти");

        Font font = new Font("Book Antiqua", Font.PLAIN,14);

        //Вводим параметры полей
        logInLabel.setBounds(X_LABEL, 50, WIDTH_LABEL, HEIGHT);
        logInTF.setBounds(X_TF,50, WIDTH_TF, HEIGHT);
        logInLabel.setFont(font);
        logInTF.setFont(font);

        passwordLabel.setBounds(X_LABEL,100, WIDTH_LABEL, HEIGHT);
        passwordPF.setBounds(X_TF,100, WIDTH_TF, HEIGHT);
        passwordLabel.setFont(font);
        passwordPF.setEchoChar('\u2022');

        logInButton.setBounds(X_TF,150, WIDTH_TF, HEIGHT);
        logInButton.setFocusPainted(false);
        logInButton.setBackground(Color.ORANGE);

        //Добавляем объекты
        add(logInLabel);
        add(logInTF);

        add(passwordLabel);
        add(passwordPF);

        add(logInButton);
    }

    public static LogInPanel getInstance() {
        if (instance == null)
            instance = new LogInPanel();
        return instance;
    }

    public String getLogIn() {
        return logInTF.getText();
    }

    public void setLogIn(String text) {
        this.logInTF.setText(text);
    }

    public char[] getPassword() {
        return passwordPF.getPassword();
    }

    public void setPassword(String text) {
        this.passwordPF.setText(text);
    }

    public void addLogInPanelListener(ActionListener listener) {
        logInButton.addActionListener(listener);
    }
}
