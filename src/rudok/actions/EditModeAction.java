package rudok.actions;

import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditModeAction extends AbstractRudokAction{
    public EditModeAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons/edit.png"));
        putValue(NAME, "EditMode");
        putValue(SHORT_DESCRIPTION, "Edit mode");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentetion().startEditState();
    }
}
