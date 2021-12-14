package rudok.actions;

import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectSlotAction extends AbstractRudokAction{
    public SelectSlotAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/selectSlot.png"));
        putValue(NAME,"SelectSlot");
        putValue(SHORT_DESCRIPTION,"Select slot");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentetion().startSelectSlotState();
    }
}
