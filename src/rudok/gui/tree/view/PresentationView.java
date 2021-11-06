package rudok.gui.tree.view;

import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Slide;
import rudok.observer.ISubscriber;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PresentationView extends JPanel implements ISubscriber {

    private Presentation model;
    private JLabel autor  = new JLabel("");
    private JPanel slides = new JPanel();

    public PresentationView(Presentation model){
        model.addSubscriber(this);
        this.model=model;
        this.setLayout(new BorderLayout());
        slides.setLayout(new BoxLayout(slides,BoxLayout.PAGE_AXIS));
        gui();
    }
    private void gui()
    {
        this.removeAll();
        autor.setText(model.getAutor());
        this.add(autor,BorderLayout.NORTH);
        slides.removeAll();
        slides.setBackground(Color.GREEN);
        for(RuNode ruNode : model.getChildern())
        {
            if(ruNode instanceof Slide){
                slides.add((SlideView)ruNode.getSubscribers().get(0));
            }
        }
        this.add(slides,BorderLayout.CENTER);
    }

    @Override
    public void update(Object notification) {
        gui();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
    }
}
