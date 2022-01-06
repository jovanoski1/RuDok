package rudok.view;

import javax.swing.*;

public class Toolbar extends JToolBar {

    public Toolbar(){

        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        add(MainFrame.getInstance().getActionManager().getSharePresentationAction());
        add(MainFrame.getInstance().getActionManager().getEditAutorAction());
        add(MainFrame.getInstance().getActionManager().getEditImageAction());
        add(MainFrame.getInstance().getActionManager().getInfoAction());

        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

}
