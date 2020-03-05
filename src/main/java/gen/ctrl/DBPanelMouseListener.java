package gen.ctrl;

import gen.Model;
import gen.View;
import gen.uip.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DBPanelMouseListener extends MouseAdapter {

    private View theView;
    private Model theModel;

    public DBPanelMouseListener(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getClickCount() == 2) {
            AdditionalFrame af = new AdditionalFrame();
            // Insert to AdditionalFrame a panel what we need
            af.setPanel(FormPanel.getInstance());
            // Add the panel to object
            af.add(af.getPanel());
            // Set visible
            af.getPanel().setVisible(true);
            // Create a thread
            Thread th = new Thread(af);
            th.start();

            // Double click check
            System.out.println("Double click is working!");
        }
    }
}
