package gen;

import data.obj.Person;
import data.services.PersonReader;
import data.services.PersonWriter;

import java.io.*;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.TreeSet;

public class Model {

    private char[] login            = new char[]{'l','o','g','i','n'};
    private char[] password         = new char[]{'l','o','g','i','n'};
    private boolean isPasswordRight = false;

    private boolean isDataSaved = false;

    private String messageForUser;

    private final String DATA_PATH       = "src/main/java/data/files";
    private final String DATA_LIST       = "data_list.dat";
    private final String DATA_LIST_EXTRA = "data_list_extra.dat"; // not used

    // A method set an user message
    public void setMessageForUser(String message) {
        this.messageForUser = message;
    }

    public String getMessageForUser() {
        return messageForUser;
    }

    public boolean isPasswordRight() {
        return isPasswordRight;
    }

    public void setLoginAndPassword(String login, char[] password) {
        checkLoginAndPassword(login, password);
    }

    private void checkLoginAndPassword(String login, char[] password) {
        if (login.length() == this.login.length && password.length == this.password.length) {
            int countTrueLogin = 0;
            int countTruePassword = 0;
            for (int i = 0; i < this.login.length; i++) {
                if (this.login[i] != login.charAt(i)) break;
                else countTrueLogin++;
            }
            if (countTrueLogin == this.login.length) {
                for (int i = 0; i < this.password.length; i++) {
                    if (this.password[i] != password[i]) break;
                    else countTruePassword++;
                }
            }
            if (countTrueLogin == this.login.length && countTruePassword == this.password.length)
                isPasswordRight = true;
        }
    }

    /**
     *
     * @param personData contains: last name, first name, father name, sex, birthDate
     *                     (An order the same)
     * @see gen.uip.FormPanel
     */
    public void setDataAndSave(String[] personData) throws IOException {
        PersonWriter pw = new PersonWriter(Paths.get(DATA_PATH, DATA_LIST));
        pw.write(personData);
        pw.close();

        if (pw.isWritten()) {
            isDataSaved = true;
            setMessageForUser("   " + personData[0] + " " +
                    personData[1].substring(0, 1) + '.' +
                    personData[1].substring(0, 1) + '.' +
                    " сохранён(а)");
        }
    }

    public boolean isDataSaved() {
        return isDataSaved;
    }

    public void setDataSaved(boolean isDataSaved) {
        this.isDataSaved = isDataSaved;
    }

    /* Reads a data list and create a person list as TreeSet collection which
       sorted by ID of person */
    public TreeMap<Integer, Person> getPersonList() throws IOException {
        PersonReader pr = new PersonReader(Paths.get(DATA_PATH, DATA_LIST));
        pr.read();
        pr.close();

        return pr.getPersonTreeMap();
    }
}
