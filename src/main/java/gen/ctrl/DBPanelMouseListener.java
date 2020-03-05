package gen.ctrl;

import gen.Model;
import gen.View;
import gen.uip.*;

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
            /* We can create the settings of object (title or size for example).
               Create it after this comment*/
            af.setTitle("Editing of person info");
            af.setPanel(FormPanel.getInstance());
            af.add(af.getPanel());
            /* We can create the settings of Panel (colors for example).
               Create it after this comment*/
            af.getPanel().setVisible(true);
            // Create a thread
            Thread th = new Thread(af);
            th.start();

            //

            // Double click check
            System.out.println("Double click is working!");
        }
    }
}
