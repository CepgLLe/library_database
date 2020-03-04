package gen.ctrl;

import gen.View;
import gen.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FormPanelListener implements ActionListener, CtrlInterface {

    private View theView;
    private Model theModel;

    public FormPanelListener(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            switch (ae.getActionCommand()) {
                case ("Назад") : // "Back"
                    clearMessageForUser();
                    changePanel(theView.getMenuPanel());
                    clearMessageForUser();

                    // Reset data to default condition and clear text fields.
                    theView.resetAllDataFormPanel();

                    // Check button
                    System.out.println("The \"Назад\" button is working!");
                    break;
                case ("Сохранить") : // "Save"
                    String[] personData = theView.getPersonData();
                    if (theView.isFormReadyForSave()) {
                        theModel.setDataAndSave(personData);

                        /* If employee saved to file (It's checking in the gen.Model) these methods
                           set default values, clear all text fields in Form Panel and then
                           change condition false. */
                        if (theModel.isDataSaved()) {
                            theModel.setDataSaved(false);
                            changePanel(theView.getMenuPanel());
                            theView.resetAllDataFormPanel();
                            theView.setMessageForUser(theModel.getMessageForUser());
                        }

                        // Check button
                        System.out.println("The \"Сохранить\" button is working!");
                    } else throw new IOException("A person data does not exist");
                    break;
                case "Обновить" : // "Update"
                    // Will create an action of button for edit a person!!!
                    break;
            }
        } catch (FileNotFoundException ex) {
            theView.displayErrorMessageDialog("Какие-то файлы не подключены!"); // "Some files is not connect!"
            System.out.println("Exception: " + ex.getMessage());
        } catch (IOException ex) {
            theView.displayErrorMessageDialog("Поблемы с передачей данных!"); // "Problems with a data transit!"
            System.out.println("Exception: " + ex.getMessage());
        } catch (Throwable th) {
            theView.displayErrorMessageDialog("Ошибка: " + th.getClass().getSimpleName()); // "Error: "...
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
