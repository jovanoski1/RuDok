package rudok.actions;

import com.sun.tools.javac.Main;
import rudok.gui.tree.model.MyTreeNode;
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
            myTreeNode.add(novi);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }
        else if(myTreeNode.getNode() instanceof Project)
        {
            Presentation presentation = new Presentation(myTreeNode.getNode(),"Mihail","Prezentacija "+(myTreeNode.getChildCount()+1),"");
            MyTreeNode novi = new MyTreeNode(presentation);
            myTreeNode.add(novi);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }
        else if(myTreeNode.getNode() instanceof Presentation)
        {
            Slide slide = new Slide(myTreeNode.getChildCount()+1, myTreeNode.getNode());
            MyTreeNode novi = new MyTreeNode(slide);
            myTreeNode.add(novi);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }
    }
}
