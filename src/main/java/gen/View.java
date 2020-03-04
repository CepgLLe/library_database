package gen;

import java.awt.event.ActionListener;
import java.util.TreeSet;

import javax.swing.*;

import data.obj.Person;
import gen.uip.*;

/**
 * This class is a view of a MVC architecture.
 */

public class View extends JFrame {

    private DBPanel dbPanel;
    private LogInPanel logInPanel;
    private MenuPanel menuPanel;
    private FormPanel formPanel;

    private JPanel panel; // пока идет работа над формой. Удалить строчку по завершению, верхнюю раскоментить!

    View() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //this.setLocation(700,100);
        this.setLocationRelativeTo(null);
        this.setResizable(false); // false не позволяет менять размер рамки
        this.setSize(600,250);
        this.setTitle("DB");
        this.setLayout(null);

        logInPanel = LogInPanel.getInstance();
        menuPanel = MenuPanel.getInstance();
        formPanel = FormPanel.getInstance();
        dbPanel = DBPanel.getInstance();

        // When work will be ready set logInPanel as start panel
        panel = menuPanel;

        this.add(panel);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public LogInPanel getLogInPanel() {
        return logInPanel;
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public FormPanel getFormPanel() {
        return formPanel;
    }

    public DBPanel getDbPanel() {
        return dbPanel;
    }

    public String getLogin() {
        return logInPanel.getLogIn();
    }

    public void setLogin(String login) {
        logInPanel.setLogIn(login);
    }

    public char[] getPassword() {
        return logInPanel.getPassword();
    }

    public void setPassword(String password) {
        logInPanel.setPassword(password);
    }

    void addLogInPanelListener(ActionListener listener) {
        logInPanel.addLogInPanelListener(listener);
    }

    void addMenuPanelListener(ActionListener listener) {
        menuPanel.addMenuPanelListener(listener);
    }

    void addFormPanelListener(ActionListener listener) {
        formPanel.addFormPanelListener(listener);
    }

    void addDBPanelListener(ActionListener listener) {
        dbPanel.addDBPanelListener(listener);
    }

    public void displayErrorMessageDialog(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
    /**
     * Далее у нас идут данные передаваемые с различных панелей (Getters),
     * которые мы передаём на gen.Controller для дальнейшей обработки.
     * <strong>На этапе разработки будут меняться!</strong>
     */

    public String getMessageForUser() {
        if (getPanel() instanceof MenuPanel)
            return menuPanel.getMessageForUser();
        else if (getPanel() instanceof DBPanel)
            return dbPanel.getMessageForUser();
        return formPanel.getMessageForUser();
    }

    public void setMessageForUser(String message) {
        if (getPanel() instanceof MenuPanel)
            menuPanel.setMessageForUser(message);
        else if (getPanel() instanceof FormPanel)
            formPanel.setMessageForUser(message);
        else if (getPanel() instanceof DBPanel)
            dbPanel.setMessageForUser(message);
    }

    public boolean isFormReadyForSave() {
        return formPanel.isFormReadyForSave();
    }

    public String[] getPersonData() {
        return formPanel.getPersonData();
    }

    public void resetAllDataFormPanel() {
        formPanel.resetAllData();
    }

    public void setPersonsListToDatabase(TreeSet<Person> personTreeSet) {
        dbPanel.createDatabase(personTreeSet);
    }
}
