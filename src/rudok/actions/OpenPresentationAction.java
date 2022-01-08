package rudok.actions;

import rudok.view.popup.OpenPresentationDialog;

import java.awt.event.ActionEvent;

public class OpenPresentationAction extends AbstractRudokAction{

    public OpenPresentationAction(){
        putValue(SMALL_ICON, loadIcon("icons/open_project.png"));
        putValue(NAME, "Open presentation");
        putValue(SHORT_DESCRIPTION, "Open presentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new OpenPresentationDialog();
    }
}
