package rudok.state;

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
        cards.removeAll();
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

        Presentation presentation = MainFrame.getInstance().getProjectView().getPresentetion();
        if(presentation==null)return;
        cards.setLayout(new CardLayout());
        for(RuNode ruNode:presentation.getChildern()){
            if(ruNode instanceof Slide){
                cards.add((SlideView)ruNode.getSubscribers().get(2));
            }
        }
        MainFrame.getInstance().getSlideShowPanel().add(cards,BorderLayout.CENTER);
        MainFrame.getInstance().getSlideShowPanel().add(navigationPanel,BorderLayout.SOUTH);

        MainFrame.getInstance().setContentPane(MainFrame.getInstance().getSlideShowPanel());
        MainFrame.getInstance().setJMenuBar(null);
        MainFrame.getInstance().invalidate();
        MainFrame.getInstance().validate();
        System.out.println("SLIDESHOW MODE");
    }
}
