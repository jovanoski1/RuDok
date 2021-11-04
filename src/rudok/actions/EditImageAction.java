package rudok.actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditImageAction extends AbstractRudokAction{

    public EditImageAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/edit-image.png"));
        putValue(NAME,"Edit image");
        putValue(SHORT_DESCRIPTION,"Edit image");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Edit image");
        dialog.setLocationRelativeTo(dialog.getParent());
        dialog.setSize(250,250);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setVisible(true);
    }
}
