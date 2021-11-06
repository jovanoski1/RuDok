package rudok.gui.tree.view;

import rudok.model.workspace.Slide;
import rudok.observer.ISubscriber;

import javax.swing.*;

public class SlideView extends JPanel implements ISubscriber {

    private Slide model;

    public SlideView(Slide model){
        this.model=model;
        model.addSubscriber(this);
        this.add(new JLabel(String.valueOf(model.getRedniBroj())));
    }


    @Override
    public void update(Object notification) {

    }
}
