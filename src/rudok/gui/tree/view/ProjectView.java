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

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
    }

    private void gui()
    {
        this.removeAll();
        name.setText(model.getIme());
        this.add(name);
        name.setBackground(Color.YELLOW);
        tabs.removeAll();
        for(RuNode ruNode:model.getChildern())
        {
            if(ruNode instanceof Presentation){
                Presentation presentation = (Presentation) ruNode;
                PresentationView presentationView = (PresentationView)presentation.getSubscribers().get(0);
                JScrollPane jScrollPane = new JScrollPane(presentationView,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                tabs.addTab(presentation.getIme(),jScrollPane);
            }
        }
        this.add(tabs);
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
