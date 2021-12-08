package rudok.actions;

import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteSlotModeAction extends AbstractRudokAction{
    public DeleteSlotModeAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons/minusSlot.png"));
        putValue(NAME, "DeleteSlotMode");
        putValue(SHORT_DESCRIPTION, "Delete slot mode");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentetion().startDeleteSlotState();
    }
}
