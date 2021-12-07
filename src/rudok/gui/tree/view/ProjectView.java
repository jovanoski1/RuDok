package rudok.gui.tree.view;

import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.model.workspace.dummyPresentation;
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
                //JScrollPane jScrollPane = new JScrollPane(presentationView,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
        //this.model.addSubscriber(this);
    }

    public void changeNameOfTab(int index,String name){
        tabs.setTitleAt(index,name);
    }

    @Override
    public void update(Object notification) {
        if(model==null)return;
        if(notification instanceof dummyPresentation){
            if(!((dummyPresentation) notification).getPresentation().getParent().equals(MainFrame.getInstance().getProjectView().getModel()))return;
            if(((dummyPresentation) notification).getStatus().equals("added")){
                Presentation presentation = ((dummyPresentation) notification).getPresentation();
                PresentationView presentationView = (PresentationView)presentation.getSubscribers().get(0);
                ///JScrollPane jScrollPane = new JScrollPane(presentationView,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                tabs.addTab(presentation.getIme(),presentationView);
                return;
            }
            else{
                Presentation presentation = ((dummyPresentation) notification).getPresentation();
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
