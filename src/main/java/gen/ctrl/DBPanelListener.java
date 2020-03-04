package gen.ctrl;

import gen.View;
import gen.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DBPanelListener implements ActionListener, CtrlInterface {

    private View theView;
    private Model theModel;

    public DBPanelListener(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            switch (ae.getActionCommand()) {
                case ("Назад") :

                    clearMessageForUser();
                    changePanel(theView.getMenuPanel());
                    clearMessageForUser();

                    // Check button
                    System.out.println("The \"Назад\" button is working!");
                    break;
                case ("Кнопка") :
                    // reserved
                    break;
            }
        } catch (Throwable th) {
            theView.displayErrorMessageDialog("Странныя ошибка тут: " + th.getClass().getSimpleName());
            //System.out.println("Throwable: " + th.getMessage());
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
