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
        /*cards.removeAll();
        navigationPanel.removeAll();
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.next(cards);
        });
        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) (cards.getLayout());
            cl.previous(cards);
        });
        navigationPanel.add(previousButton);
        navigationPanel.add(nextButton);

        PresentationView presentation = MainFrame.getInstance().getProjectView().getPresentetion();
        if(presentation==null)return;
        cards.setLayout(new CardLayout());
        for(RuNode ruNode:presentation.getModel().getChildern()){
            if(ruNode instanceof Slide){
                cards.add((SlideView)ruNode.getSubscribers().get(2));
            }
        }*/
        PresentationView presentation = MainFrame.getInstance().getProjectView().getPresentetion();
        if(presentation==null)return;
        presentation.removeAll();
        //presentation.getSlideShowPanel().add(cards,BorderLayout.CENTER);
        //presentation.getSlideShowPanel().add(navigationPanel, BorderLayout.SOUTH);
        presentation.add(presentation.getSlideShowPanel(),BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(presentation);
        System.out.println("SLIDESHOW MODE");
    }
}
