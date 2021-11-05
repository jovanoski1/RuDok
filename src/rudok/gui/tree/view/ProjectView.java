package rudok.gui.tree.view;

import rudok.model.workspace.Project;
import rudok.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel implements ISubscriber {

    private Label name = new Label("");
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
    }
}
