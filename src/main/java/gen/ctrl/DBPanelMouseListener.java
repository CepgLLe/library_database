package gen.ctrl;

import gen.Model;
import gen.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DBPanelMouseListener extends MouseAdapter implements CtrlInterface {

    private View theView;
    private Model theModel;

    public DBPanelMouseListener(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getClickCount() == 2)
            System.out.println("Double click is working!");
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
