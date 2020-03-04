package gen.ctrl;

import gen.View;
import gen.Model;

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
                case ("Войти") : // "To come in"
                    // Transit login and password to the Model
                    login = theView.getLogin();
                    password = theView.getPassword();
                    theModel.setLoginAndPassword(login, password);

                    // check password
                    if (theModel.isPasswordRight()) {
                        // Go to MenuPanel
                        changePanel(theView.getMenuPanel());
                    } else {
                        /* If login/password is invalid reset fields
                           and throw an error message for user */
                        theView.setLogin("");
                        theView.setPassword("");
                        theView.displayErrorMessageDialog("Неверный логин или пароль"); // Invalid login or password
                    }
                    System.out.println("The \"Войти\"  button is working!");
                    break;
                case ("Кнопка") : // "Button"
                    // reserved
                    break;
            }
        } catch (Throwable th) {
            theView.displayErrorMessageDialog("Оштбка: " + th.getClass().getSimpleName()); // "Error: "...
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
