package rudok.gui.actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

public class InfoAction extends AbstractRudokAction{

    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/info_icon.png"));
        putValue(NAME,"Info");
        putValue(SHORT_DESCRIPTION,"Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Info");
        JLabel label = new JLabel("Mihail Jovanoski RN 15/20");
        URL url = getClass().getResource("icons/slika.png");
        if(url==null){
            System.err.println("Resource not found: icons/slika.png");
            return;
        }
        JLabel slika = new JLabel(new ImageIcon(url));


        dialog.add(label, BorderLayout.NORTH);
        dialog.add(slika, BorderLayout.CENTER);

        dialog.setLocationRelativeTo(dialog.getParent());
        dialog.setSize(250,250);
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setVisible(true);
    }
}
