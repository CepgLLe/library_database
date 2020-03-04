package gen.uip;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A FormPanel is a panel for create a new person data and saving it to database.
 */

public class FormPanel extends JPanel {

    private static FormPanel instance;

    private final int HEIGHT      = 25;
    private final int WIDTH_LABEL = 110;
    private final int WIDTH_TF    = 150;
    private final int X_LABEL     = 15;
    private final int X_TF        = 130;

    private JLabel messageForUser;

    private JLabel sexLabel;
    private JLabel lastNameLabel;
    private JLabel firstNameLabel;
    private JLabel fatherNameLabel;
    private JLabel dateOfBirthLabel;
    //private JLabel positionLabel;

    private JTextField lastNameTF;
    private JTextField firstNameTF;
    private JTextField fatherNameTF;
    //private JTextField positionTF;

    private String sex;
    private JComboBox<String> sexSelector;

    private LocalDate dateOfBirth;
    private JComboBox<Integer> daysOfBirthSelector;
    private JComboBox<String> monthsOfBirthSelector;
    private JComboBox<Integer> yearsOfBirthSelector;

    private JButton toMenuButton;
    private JButton saveHumanInfoButton;

    private boolean isValidLastName;
    private boolean isValidFirstName;
    private boolean isValidFatherName;
    private boolean isValidBirthDate;

    /**
     * The array contain data only for person.
     * @param data[0] = lastNameTF.getText();
     * @param data[1] = firstNameTF.getText();
     * @param data[2] = fatherNameTF.getText();
     * @param data[3] = sex;
     * @param data[4] = birthDate; is a string type with pattern: dd.MM.yyyy
     */
    private String[] personData;

    // Will create!!!!!
    private String[] extraPersonData;

    private FormPanel() {
        setBackground(Color.LIGHT_GRAY);
        setSize(584,211); // Inside a border: width of window minus 16, height if window minus 39
        setLayout(null);

        messageForUser      = new JLabel("");

        sexLabel            = new JLabel("Пол:");           // "Sex:"
        lastNameLabel       = new JLabel("Фамилия:");       // "Surname:"
        firstNameLabel      = new JLabel("Имя:");           // "First name:"
        fatherNameLabel     = new JLabel("Отчество:");      // "Father's name:" (in Russia & CIS)
        dateOfBirthLabel    = new JLabel("Дата рождения:"); // "Birth Date:"
        //positionLabel       = new JLabel("Должность:");

        lastNameTF          = new JTextField();
        firstNameTF         = new JTextField();
        fatherNameTF        = new JTextField();
        //positionTF          = new JTextField();

        toMenuButton        = new JButton("Назад");
        saveHumanInfoButton = new JButton("Сохранить");

        dateOfBirth = LocalDate.of(LocalDate.now().getYear(),1, 1);

        /* Assign values for invalid condition. In that condition an uses will not
           save empty fields. */
        isValidLastName    = false;
        isValidFirstName   = false;
        isValidFatherName  = false;
        isValidBirthDate   = true;

        Font font = new Font("Book Antiqua", Font.PLAIN,14);

        messageForUser.setBounds(130, 10, 325, 25);
        messageForUser.setOpaque(true); // false for transparent a text field
        messageForUser.setBackground(Color.ORANGE); // color for a text area
        messageForUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        InputDataListener inputDataListener = new InputDataListener();

        createSexSelection();
        sexLabel   .setBounds(X_TF + WIDTH_TF + 10,40, WIDTH_LABEL, HEIGHT);
        sexLabel   .setFont(font);
        sexSelector.setBounds(X_TF + WIDTH_TF + 45,40,45, HEIGHT);
        sexSelector.addActionListener(new FormActionListener());

        lastNameLabel.setBounds(X_LABEL,40, WIDTH_LABEL, HEIGHT);
        lastNameLabel.setFont(font);
        lastNameTF   .setBounds(X_TF,40, WIDTH_TF, HEIGHT);
        lastNameTF   .setFont(font);
        lastNameTF   .getDocument().addDocumentListener(inputDataListener);

        firstNameLabel.setBounds(X_LABEL,70, WIDTH_LABEL, HEIGHT);
        firstNameLabel.setFont(font);
        firstNameTF   .setBounds(X_TF,70, WIDTH_TF, HEIGHT);
        firstNameTF   .setFont(font);
        firstNameTF   .getDocument().addDocumentListener(inputDataListener);

        fatherNameLabel.setBounds(X_LABEL,100, WIDTH_LABEL, HEIGHT);
        fatherNameLabel.setFont(font);
        fatherNameTF   .setBounds(X_TF,100, WIDTH_TF, HEIGHT);
        fatherNameTF   .setFont(font);
        fatherNameTF   .getDocument().addDocumentListener(inputDataListener);

        dateOfBirthLabel.setBounds(X_LABEL,130, WIDTH_LABEL, HEIGHT);
        dateOfBirthLabel.setFont(font);

        createSelectionOfBirthDate();
        daysOfBirthSelector  .setBounds(X_TF,130,45, HEIGHT);
        daysOfBirthSelector  .addActionListener(new FormActionListener());
        monthsOfBirthSelector.setBounds(X_TF + 50,130,80, HEIGHT);
        monthsOfBirthSelector.addActionListener(new FormActionListener());
        yearsOfBirthSelector .setBounds(X_TF + 135,130,55, HEIGHT);
        yearsOfBirthSelector .addActionListener(new FormActionListener());

//        positionLabel.setBounds(X_LABEL,160, WIDTH_LABEL, HEIGHT);
//        positionLabel.setFont(font);
//        positionTF   .setBounds(X_TF,160, WIDTH_TF, HEIGHT);
//        positionTF   .setFont(font);

        saveHumanInfoButton.setBounds(460,10, WIDTH_LABEL, HEIGHT);
        saveHumanInfoButton.setFocusPainted(false);
        saveHumanInfoButton.setBackground(Color.ORANGE);

        toMenuButton.setBounds(X_LABEL,10, WIDTH_LABEL, HEIGHT);
        toMenuButton.setFocusPainted(false);
        toMenuButton.setBackground(Color.ORANGE);

        //Добавляем объекты
        add(messageForUser);

        add(sexLabel);
        add(sexSelector);

        add(lastNameLabel);
        add(lastNameTF);

        add(firstNameLabel);
        add(firstNameTF);

        add(fatherNameLabel);
        add(fatherNameTF);

        add(dateOfBirthLabel);
        add(daysOfBirthSelector);
        add(monthsOfBirthSelector);
        add(yearsOfBirthSelector);

//        add(positionLabel);
//        add(positionTF);

        add(saveHumanInfoButton);
        add(toMenuButton);
    }

    // Singleton
    public static FormPanel getInstance() {
        if (instance == null)
            instance = new FormPanel();
        return instance;
    }

    /* A method add a listener for buttons. When will be check all data and push
       the "Save" button, will be create a employee object. @see [setEmployee()] */
    public void addFormPanelListener(ActionListener listener) {
        toMenuButton.addActionListener(listener);
        saveHumanInfoButton.addActionListener(listener);
    }

    // See a person data field
    public String[] getPersonData() {
        personData = new String[]{
                lastNameTF  .getText(),
                firstNameTF .getText(),
                fatherNameTF.getText(),
                sex,
                dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        };
        return personData;
    }

    // A method set default values and clear all text fields.
    public void resetAllData() {
        messageForUser.setText("");
        lastNameTF    .setText("");
        firstNameTF   .setText("");
        fatherNameTF  .setText("");
        daysOfBirthSelector.setSelectedIndex(0);
        monthsOfBirthSelector.setSelectedIndex(0);
        yearsOfBirthSelector.setSelectedIndex(0);
        dateOfBirth = dateOfBirth.withYear((Integer) yearsOfBirthSelector.getSelectedItem());
        dateOfBirth = dateOfBirth.withMonth(monthsOfBirthSelector.getSelectedIndex() + 1);
        dateOfBirth = dateOfBirth.withDayOfMonth((Integer) daysOfBirthSelector.getSelectedItem());
        sexSelector.setSelectedIndex(0);
        sex = (String) sexSelector.getSelectedItem();
    }

    public boolean isFormReadyForSave() {
        return !sex.equals("-") &&
                isValidLastName &&
                isValidFirstName &&
                isValidFatherName &&
                isValidBirthDate;
    }

    /* A class (Listener) controls character input of test fields (lastNameTF, firstNameTF, fatherNameTF)
       and checks fields for validity */
    private class InputDataListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            changedUpdate(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            changedUpdate(e);
        }

        // A method reacts on adding and deleting characters in text fields
        @Override
        public void changedUpdate(DocumentEvent e) {
            if (e.getDocument() == lastNameTF.getDocument()) checkData(lastNameTF);
            else if (e.getDocument() == firstNameTF.getDocument()) checkData(firstNameTF);
            else if (e.getDocument() == fatherNameTF.getDocument()) checkData(fatherNameTF);
        }

        /* A method return true, if text match a pattern of regular expressions.
           Works with Russian language. (yet!) */
        private void checkData(JTextField tf) {
            Pattern p;
            Matcher m = null;
            if (tf.getText().length() == 0) {
                tf.setBackground(Color.WHITE);
                setMessageForUser("");
            } else if (tf == lastNameTF) {
                p = Pattern.compile("^[А-Я][а-я]+(-[А-Я][а-я]+)?");
                m = p.matcher(tf.getText());
                isValidLastName = m.matches();
            } else if (tf == firstNameTF) {
                p = Pattern.compile("^[А-Я][а-я]+");
                m = p.matcher(tf.getText());
                isValidFirstName = m.matches();
            } else if (tf == fatherNameTF) {
                p = Pattern.compile("^[А-Я][а-я]+");
                m = p.matcher(tf.getText());
                isValidFatherName = m.matches();
            }

            if (m != null && !m.matches()) {
                tf.setBackground(new Color(255,198,198));
                setMessageForUser("  Проверьте правильность ввода!"); // "Check that input is correct!"
            } else {
                tf.setBackground(Color.WHITE);
                setMessageForUser("");
            }
            //System.out.println(isValidLastName + " " + isValidFirstName + " " + isValidFatherName);
        }
    }

    // A method create a selection for JComboBox of birthday date.
    private void createSelectionOfBirthDate() {
        Integer[] dayOfMonth = new Integer[31];
        for (int i = 0; i < dayOfMonth.length; i++) dayOfMonth[i] = i + 1;

        String[] months = {"январь", "февраль", "март", "апрель", "май", "июнь",
                "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};

        int thisYear = LocalDate.now().getYear();
        Integer[] years = new Integer[100];
        for (int i = 0; i < years.length; i++) years[i] = thisYear - i;

        daysOfBirthSelector   = new JComboBox<>(dayOfMonth);
        monthsOfBirthSelector = new JComboBox<>(months);
        yearsOfBirthSelector  = new JComboBox<>(years);
    }

    private void createSexSelection() {
        String[] sex = {"-", "М", "Ж"}; // "М" - means a male & "Ж" - means a female
        sexSelector  = new JComboBox<>(sex);
        this.sex     = sex[0];
    }

    /* A class (Listener) works with check boxes (birth date & sex).
       If the date is not correct a background of check boxes will change to red
       and the person data will not saving. */
    private class FormActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if (ae.getSource() == daysOfBirthSelector) {
                    dateOfBirth = dateOfBirth.withDayOfMonth((Integer) daysOfBirthSelector.getSelectedItem());
                } else if (ae.getSource() == monthsOfBirthSelector) {
                    dateOfBirth = dateOfBirth.withMonth(monthsOfBirthSelector.getSelectedIndex() + 1);
                } else if (ae.getSource() == yearsOfBirthSelector) {
                    dateOfBirth = dateOfBirth.withYear((Integer) yearsOfBirthSelector.getSelectedItem());
                } else if (ae.getSource() == sexSelector) {
                    sex = (String) sexSelector.getSelectedItem();
                }

                isValidBirthDate = true;
                daysOfBirthSelector.setBackground(Color.WHITE);
                yearsOfBirthSelector.setBackground(Color.WHITE);
                yearsOfBirthSelector.setBackground(Color.WHITE);
                setMessageForUser("");
            } catch (DateTimeException ex) {
                isValidBirthDate = false;
                daysOfBirthSelector.setBackground(new Color(255,198,198));
                yearsOfBirthSelector.setBackground(new Color(255,198,198));
                yearsOfBirthSelector.setBackground(new Color(255,198,198));
                setMessageForUser("  Проверьте правильность ввода даты!"); // "Check that a date input is correct!"
                //System.out.println(dateOfBirth + " Ошибочный");
            }
        }
    }

    // A getter return a message for user
    public String getMessageForUser() {
        return this.messageForUser.getText();
    }

    // A setter set a message for user. For example the message about invalid data.
    public void setMessageForUser(String message) {
        this.messageForUser.setText(message);
    }
}
