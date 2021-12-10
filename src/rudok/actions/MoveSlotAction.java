package rudok.actions;

import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MoveSlotAction extends AbstractRudokAction{

    public MoveSlotAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/moveSlotIcon.png"));
        putValue(NAME,"MoveSlot");
        putValue(SHORT_DESCRIPTION,"Move slot");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentetion().startMoveSlotState();
    }
}
