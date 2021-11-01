package rudok.gui.actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditAutorAction extends AbstractRudokAction{

    public EditAutorAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/editAutorIcon.png"));
        putValue(NAME,"Edit autor");
        putValue(SHORT_DESCRIPTION,"Edit autor");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Edit autor");
        dialog.setLocationRelativeTo(dialog.getParent());
        dialog.setSize(250,250);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setVisible(true);
    }
}
