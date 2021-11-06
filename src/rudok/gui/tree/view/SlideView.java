package rudok.gui.tree.view;

import rudok.model.workspace.Slide;
import rudok.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JPanel implements ISubscriber {

    private Slide model;

    public SlideView(Slide model){
        this.model=model;
        model.addSubscriber(this);
        this.setSize(100,100);
        this.setBackground(Color.YELLOW);
        this.add(new JLabel(model.getIme()));
    }


    @Override
    public void update(Object notification) {

    }
}
