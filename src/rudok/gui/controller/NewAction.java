package rudok.gui.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends AbstractRudokAction{

    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/new_icon.png"));
        putValue(NAME,"New");
        putValue(SHORT_DESCRIPTION,"New");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
