package gen.ctrl;

import gen.Model;
import gen.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInPanelListener implements ActionListener, CtrlInterface {

    private View theView;
    private Model theModel;

    public LogInPanelListener(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String login;
        char[] password;
        try {
            switch (ae.getActionCommand()) {
                case ("Войти") :
                    // Получаем и отправляем пароли для сверки
                    login = theView.getLogin();
                    password = theView.getPassword();
                    theModel.setLoginAndPassword(login, password);

                    // Проверяем пароль
                    if (theModel.isPasswordRight()) {
                        // "Переходим" в меню
                        changePanel(theView.getMenuPanel());
                    } else {
                        /* Если пароль неверный, обнуляем поля для ввода
                           и выдаём ошибку */
                        theView.setLogin("");
                        theView.setPassword("");
                        theView.displayErrorMessageDialog("Неверный логин или пароль");
                    }
                    System.out.println("The \"Войти\"  button is working!");
                    break;
            }
        } catch (Throwable th) {
            theView.displayErrorMessageDialog("Странныя ошибка тут: " + th.getClass().getSimpleName());
            System.out.println("Throwable: " + th.getMessage());
        }
    }

    @Override
    public void clearMessageForUser() {
        theView.setMessageForUser("");
        theModel.setMessageForUser("");
    }

    @Override
    public void changePanel(JPanel panel) {
        theView.getPanel().setVisible(false);
        theView.setPanel(panel);
        theView.add(theView.getPanel());
        theView.getPanel().setVisible(true);
    }
}
