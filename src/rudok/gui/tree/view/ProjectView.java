package rudok.gui.tree.view;

import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.observer.ISubscriber;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel implements ISubscriber {

    private JLabel name = new JLabel("");
    private Project model;
    private JTabbedPane tabs = new JTabbedPane();

    public ProjectView(){

        this.setLayout(new BorderLayout());
    }

    private void gui()
    {
        this.removeAll();
        name.setText(model.getIme());
        this.add(name,BorderLayout.NORTH);
        name.setBackground(Color.YELLOW);
        tabs.removeAll();
        for(RuNode ruNode:model.getChildern())
        {
            if(ruNode instanceof Presentation){
                Presentation presentation = (Presentation) ruNode;
                tabs.addTab(presentation.getIme(),(PresentationView)presentation.getSubscribers().get(0));
            }
        }
        add(tabs,BorderLayout.CENTER);
    }

    public ProjectView(Project model){
        this.model = model;
    }

    public Project getModel() {
        return model;
    }

    public void setModel(Project model) {
        this.model = model;
        this.update(new Object());
        //this.model.addSubscriber(this);
    }

    @Override
    public void update(Object notification) {
        if(model==null)return;
        gui();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
    }
}
