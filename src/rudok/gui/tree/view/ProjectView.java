package rudok.gui.tree.view;

import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel implements ISubscriber {

    private JLabel name = new JLabel("");
    Project model;

    public ProjectView(Project model){
        this.model = model;
        name.setText(model.getIme());
        this.add(name,BorderLayout.NORTH);
    }


    @Override
    public void update(Object notification) {
        if(notification instanceof String)
        {
            String novoIme= (String)notification;
            model.setIme(novoIme);
            name.setText(novoIme);
        }
        else if(notification instanceof PresentationView)
        {
            this.add((PresentationView)notification);
            this.revalidate();
        }
    }
}
