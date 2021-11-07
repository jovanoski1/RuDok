package rudok.actions;

import com.sun.tools.javac.Main;
import rudok.gui.tree.model.MyTreeNode;
import rudok.gui.tree.view.PresentationView;
import rudok.gui.tree.view.ProjectView;
import rudok.gui.tree.view.SlideView;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.model.workspace.Slide;
import rudok.model.workspace.Workspace;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends AbstractRudokAction{

    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/new_icon.png"));
        putValue(NAME,"New");
        putValue(SHORT_DESCRIPTION,"New");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object p= MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(p==null) return;
        MyTreeNode myTreeNode = (MyTreeNode)p;
        if(myTreeNode.getNode() instanceof Workspace){
            Project project = new Project("Projekat "+(myTreeNode.getChildCount()+1),myTreeNode.getNode());
            MyTreeNode novi = new MyTreeNode(project);
            //novom projektu se doda jedini projectView
            project.addSubscriber(MainFrame.getInstance().getProjectView());
            myTreeNode.add(novi);
            ((Workspace) myTreeNode.getNode()).addChild(project); /// da li ovo treba
            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }
        else if(myTreeNode.getNode() instanceof Project)
        {
            if(myTreeNode.getNode().getParent()==null)return;
            Presentation presentation = new Presentation(myTreeNode.getNode(),"Mihail","Prezentacija "+(myTreeNode.getChildCount()+1),"src/rudok/images/pozadina.jpg");
            MyTreeNode novi = new MyTreeNode(presentation);
            PresentationView presentationView = new PresentationView(presentation);
            //presentation.addSubscriber(presentationView); // dali treba ovo - dodaje se u kontruktoru PresentationView-a
            myTreeNode.add(novi);
            ((Project) myTreeNode.getNode()).addChild(presentation); /// da li ovo treba pokrece i notify
            //MainFrame.getInstance().getProjectView().update(presentationView);
            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
            //SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());

        }
        else if(myTreeNode.getNode() instanceof Presentation)
        {
            Slide slide = new Slide(myTreeNode.getChildCount()+1, myTreeNode.getNode());
            MyTreeNode novi = new MyTreeNode(slide);
            SlideView slideView = new SlideView(slide);
            myTreeNode.add(novi);
            ((Presentation) myTreeNode.getNode()).addChild(slide); /// da li ovo treba
            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }
    }
}
