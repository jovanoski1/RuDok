package rudok.gui.view;

import javax.swing.*;

public class Toolbar extends JToolBar {

    public Toolbar(){

        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getInfoAction());

    }

}
