package gen.uip;

import javax.swing.*;

public class AdditionalFrame extends JFrame implements Runnable {

    private JPanel panel;

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void run() {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //this.setLocation(700,100);
        this.setLocationRelativeTo(null);
        this.setResizable(false); // If false - not resizable
        this.setSize(600,250);
        this.setTitle("DB");
        this.setLayout(null);

        this.setVisible(true);
    }
}
