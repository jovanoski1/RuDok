package rudok.gui.tree.view;

import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Slide;
import rudok.observer.ISubscriber;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class PresentationView extends JPanel implements ISubscriber {

    private Presentation model;
    private JLabel autor  = new JLabel("");

    public PresentationView(Presentation model){
        model.addSubscriber(this);
        this.model=model;
        gui();
    }
    private void gui()
    {
        autor.setText(model.getIme());
        this.add(autor);
        for(RuNode ruNode : model.getChildern())
        {
            if(ruNode instanceof Slide){
                this.add((SlideView)ruNode.getSubscribers().get(0));
            }
        }
    }

    @Override
    public void update(Object notification) {
        gui();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
    }
}
