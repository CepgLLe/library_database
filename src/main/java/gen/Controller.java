package gen;

import gen.ctrl.LogInPanelListener;
import gen.ctrl.MenuPanelListener;
import gen.ctrl.FormPanelListener;
import gen.ctrl.DBPanelListener;

public class Controller {

    private View theView;
    private Model theModel;

    public Controller(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addLogInPanelListener(new LogInPanelListener(this.theView, this.theModel));
        this.theView.addMenuPanelListener(new MenuPanelListener(this.theView, this.theModel));
        this.theView.addFormPanelListener(new FormPanelListener(this.theView, this.theModel));
        this.theView.addDBPanelListener(new DBPanelListener(this.theView, this.theModel));
    }
}
