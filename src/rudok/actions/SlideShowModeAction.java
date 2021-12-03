package rudok.actions;

import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SlideShowModeAction extends AbstractRudokAction{
    public SlideShowModeAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons/slideshow.png"));
        putValue(NAME, "SlideShowMode");
        putValue(SHORT_DESCRIPTION, "Slideshow mode");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().startSlideShowState();
    }
}
