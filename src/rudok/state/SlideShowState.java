package rudok.state;

import rudok.gui.tree.view.PresentationView;
import rudok.gui.tree.view.SlideView;
import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Slide;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class SlideShowState implements State{

    private JPanel cards = new JPanel();
    private JPanel navigationPanel = new JPanel();

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
