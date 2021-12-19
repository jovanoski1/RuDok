package rudok.state;

import rudok.gui.tree.view.PresentationView;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class SlideShowState implements State{

    @Override
    public void changeMode() {
        PresentationView presentation = MainFrame.getInstance().getProjectView().getPresentetion();
        if(presentation==null)return;
        presentation.removeAll();
        presentation.add(presentation.getSlideShowPanel(),BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(presentation);
        System.out.println("SLIDESHOW MODE");
    }
}
