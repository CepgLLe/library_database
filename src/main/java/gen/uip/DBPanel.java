package gen.uip;

import data.obj.Person;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * A DBPanel is a panel which output a persons list and give control to use about this list
 */

public class DBPanel extends JPanel {

    private static DBPanel instance;

    private JLabel messageForUser;

    private String[] dbHeadsColumns;
    private Object[][] data;

    private JTable dbTable;
    private JScrollPane dbTableScroll;

    private TreeMap<Integer, Person> personTreeMap;

    private JButton toMenuButton;

    private DBPanel() {
        setBackground(Color.LIGHT_GRAY);
        setSize(584,211); // Inside a border: width of window minus 16, height of window minus 39
        setLayout(null);

        messageForUser = new JLabel("");

        dbHeadsColumns = new String[]{"Фамилия", "Имя", "Отчество", "Пол", "Возраст", "Дата рождения"};
        createTableWithNullData();
        dbTable = new JTable(data, dbHeadsColumns);
        dbTableScroll = new JScrollPane(dbTable);

        toMenuButton = new JButton("Назад"); // "Back"

        messageForUser.setBounds(130, 10, 325, 25);
        messageForUser.setOpaque(true); // false for transparent a text field
        messageForUser.setBackground(Color.ORANGE); // Set an orange background
        messageForUser.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Set a black border
        
        dbTableScroll.setBounds(10,45,564,150);
        dbTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        toMenuButton.setBounds(10,10, 110, 25);
        toMenuButton.setFocusPainted(false);
        toMenuButton.setBackground(Color.ORANGE);

        add(messageForUser);
        add(dbTableScroll);

        add(toMenuButton);
    }

    // Singleton
    public static DBPanel getInstance() {
        if (instance == null)
            instance = new DBPanel();
        return instance;
    }

    public void addDBPanelListener(ActionListener listener) {
        toMenuButton.addActionListener(listener);
    }

    public void addDBPanelMouseListener (MouseListener ml) {
        dbTable.addMouseListener(ml);
    }

    private void createTableWithNullData() {
        if (data == null)
            data = new Object[50][6];
    }

    /**
     * @param personTreeMap contains all persons from data list. Sorted by ID.
     */
    public void createDatabase(TreeMap<Integer, Person> personTreeMap) {
        this.personTreeMap = personTreeMap;

        data = new Object[this.personTreeMap.size()][6];
        int index = 0;
        for (Map.Entry<Integer, Person> pair : this.personTreeMap.entrySet()) {
            if (index < this.personTreeMap.size()) {
                data[index][0] = pair.getValue().getLastName();
                data[index][1] = pair.getValue().getFirstName();
                data[index][2] = pair.getValue().getFatherName();
                data[index][3] = pair.getValue().getSex();
                data[index][4] = pair.getValue().getAge();
                data[index][5] = pair.getValue().getBirthDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            }
            index++;
        }
        DefaultTableModel dtm = new DefaultTableModel(data, dbHeadsColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        dbTable.setModel(dtm);
        dbTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                                                           Object value,
                                                           boolean isSelected,
                                                           boolean hasFocus,
                                                           int row, int column) {
                JLabel label = (JLabel) super.
                        getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                return label;
            }
        });
    }

    public String getMessageForUser() {
        return this.messageForUser.getText();
    }

    public void setMessageForUser(String message) {
        this.messageForUser.setText(message);
    }

    public void setData(Object[][] data) {
        this.data = data;
    }

    public TreeMap<Integer, Person> getPersonTreeMap() {
        return personTreeMap;
    }
}
