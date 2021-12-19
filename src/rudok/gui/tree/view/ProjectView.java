package rudok.gui.tree.view;

import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.model.workspace.dummyTreeNotification;
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
                tabs.addTab(presentation.getIme(),presentationView);
            }
        }
        this.add(tabs);
    }
    public PresentationView getPresentetion(){
        if(tabs.getSelectedComponent()==null)return null;
        return ((PresentationView) tabs.getSelectedComponent());
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
    }

    public void changeNameOfTab(int index,String name){
        tabs.setTitleAt(index,name);
    }

    @Override
    public void update(Object notification) {
        if(model==null)return;
        if(notification instanceof dummyTreeNotification){
            if(!((dummyTreeNotification) notification).getTreeNode().getParent().equals(MainFrame.getInstance().getProjectView().getModel()))return;
            if(((dummyTreeNotification) notification).getStatus().equals("added")){
                Presentation presentation = (Presentation) ((dummyTreeNotification) notification).getTreeNode();
                PresentationView presentationView = (PresentationView)presentation.getSubscribers().get(0);
                tabs.addTab(presentation.getIme(),presentationView);
                return;
            }
            else{
                Presentation presentation = (Presentation)((dummyTreeNotification) notification).getTreeNode();
                for(int i=0;i<tabs.getTabCount();i++){
                    PresentationView pt = (PresentationView) tabs.getComponentAt(i);
                    System.out.println(pt.getModel().getIme());
                    if(pt.getModel().equals(presentation)) {tabs.removeTabAt(i);break;}
                }
                return;
            }
        }
        if(notification instanceof String){
            name.setText((String) notification);
            return;
        }
        gui();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
    }
}
