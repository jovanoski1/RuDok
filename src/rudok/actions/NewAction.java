package rudok.actions;

import com.sun.tools.javac.Main;
import rudok.gui.tree.model.MyTreeNode;
import rudok.gui.tree.view.PresentationView;
import rudok.gui.tree.view.ProjectView;
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
            project.addSubscriber(new ProjectView(project));
            myTreeNode.add(novi);
            ((Workspace) myTreeNode.getNode()).addChild(project); /// da li ovo treba
            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }
        else if(myTreeNode.getNode() instanceof Project)
        {
            Presentation presentation = new Presentation(myTreeNode.getNode(),"Mihail","Prezentacija "+(myTreeNode.getChildCount()+1),"");
            MyTreeNode novi = new MyTreeNode(presentation);
            PresentationView presentationView = new PresentationView(presentation);
            presentation.addSubscriber(presentationView);
            myTreeNode.add(novi);
            ((Project) myTreeNode.getNode()).addChild(presentation); /// da li ovo treba
            myTreeNode.getNode().notifySubscribers(presentationView); //notify projekat da je dodata prezentacija
            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }
        else if(myTreeNode.getNode() instanceof Presentation)
        {
            Slide slide = new Slide(myTreeNode.getChildCount()+1, myTreeNode.getNode());
            MyTreeNode novi = new MyTreeNode(slide);
            myTreeNode.add(novi);
            ((Presentation) myTreeNode.getNode()).addChild(slide); /// da li ovo treba
            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }
    }
}
