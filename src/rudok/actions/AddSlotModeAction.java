package rudok.actions;

import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddSlotModeAction extends AbstractRudokAction{

    public AddSlotModeAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons/addSlot.png"));
        putValue(NAME, "AddSlotMode");
        putValue(SHORT_DESCRIPTION, "Add slot mode");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentetion().startAddSlotState();
    }
}
