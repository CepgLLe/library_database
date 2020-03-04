package gen.ctrl;

import gen.View;
import gen.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanelListener implements ActionListener, CtrlInterface {

    private View theView;
    private Model theModel;

    public MenuPanelListener(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            switch (ae.getActionCommand()) {
                case ("Форма для заполнения") :
                    // Go to FormPanel
                    changePanel(theView.getFormPanel());
                    // Check button
                    System.out.println("The \"Форма для заполнения\" button is working!");
                    break;
                case ("База данных") : // "Database"

                    // Go to DbPanel
                    changePanel(theView.getDbPanel());

                    // Reads the data_list.dat, create TreeSet and set to DbPanel for create the table
                    theView.setPersonsListToDatabase(theModel.getPersonList());

                    // Check button
                    System.out.println("The \"База данных\" button is working!");
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
