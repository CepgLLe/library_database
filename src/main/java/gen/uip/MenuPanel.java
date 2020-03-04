package gen.uip;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * A MenuPanel is a panel with buttons for transition to workspace you need
 */

public class MenuPanel extends JPanel {

    private static MenuPanel instance;

    private final int HEIGHT_BUTTONS = 25;
    private final int WIDTH_BUTTONS  = 180;
    private final int X_BUTTONS      = 202;

    private JLabel messageForUser;

    private JButton toFormButton;
    private JButton toDBButton;

    private MenuPanel() {
        setBackground(Color.LIGHT_GRAY);
        setSize(584,211); // Inside a border: width of window minus 16, height if window minus 39
        setLayout(null);

        messageForUser = new JLabel("");

        toFormButton   = new JButton("Форма для заполнения"); // "Form to fill"
        toDBButton     = new JButton("База данных"); // "Database"

        messageForUser.setBounds(10,10,564, 25);
        messageForUser.setOpaque(true); // false for transparent a text field
        messageForUser.setBackground(Color.ORANGE); // color for a text area
        messageForUser.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        toFormButton.setBounds(X_BUTTONS,50, WIDTH_BUTTONS, HEIGHT_BUTTONS);
        toFormButton.setFocusPainted(false);
        toFormButton.setBackground(Color.ORANGE);

        toDBButton.setBounds(X_BUTTONS,100, WIDTH_BUTTONS, HEIGHT_BUTTONS);
        toDBButton.setFocusPainted(false);
        toDBButton.setBackground(Color.ORANGE);

        //Add JPanel objects
        add(messageForUser);

        add(toFormButton);
        add(toDBButton);
    }

    // Singleton
    public static MenuPanel getInstance() {
        if (instance == null)
            instance = new MenuPanel();
        return instance;
    }

    public void addMenuPanelListener(ActionListener listener) {
        toFormButton.addActionListener(listener);
        toDBButton.addActionListener(listener);
    }

    public String getMessageForUser() {
        return this.messageForUser.getText();
    }

    public void setMessageForUser(String message) {
        this.messageForUser.setText(message);
    }
}
