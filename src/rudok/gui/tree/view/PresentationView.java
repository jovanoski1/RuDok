package rudok.gui.tree.view;

import rudok.model.workspace.Presentation;
import rudok.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class PresentationView extends JTabbedPane implements ISubscriber {

    private Presentation model;
    private JLabel autor  = new JLabel("");

    public PresentationView(Presentation model){
        model.addSubscriber(this);
        this.model=model;
        autor.setText(model.getIme());
        this.add(autor);
    }

    @Override
    public void update(Object notification) {
    }
}
