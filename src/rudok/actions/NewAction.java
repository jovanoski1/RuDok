package rudok.actions;

import rudok.factory.AbstractNodeFactory;
import rudok.factory.NodeFactoryChooser;
import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNode;
import rudok.model.workspace.*;
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
        AbstractNodeFactory f = NodeFactoryChooser.getFactory(myTreeNode.getType());
        if (f==null)return;
        int ind=RuNodeType.valueOf(String.valueOf(myTreeNode.getType())).ordinal();
        if(myTreeNode.getNode().getParent()==null && (myTreeNode.getParent() instanceof Workspace))return;
        MyTreeNode child = new MyTreeNode(f.getNewRuNode(myTreeNode),RuNodeType.values()[ind+1]);
        myTreeNode.add(child);
        MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());

        /*if(myTreeNode.getNode() instanceof Workspace){
            /*Project project = new Project("Projekat "+(myTreeNode.getChildCount()+1),myTreeNode.getNode());
            MyTreeNode novi = new MyTreeNode(project, RuNodeType.PROJECT);
            //novom projektu se doda jedini projectView
            project.addSubscriber(MainFrame.getInstance().getProjectView());
            myTreeNode.add(novi);
            ((Workspace) myTreeNode.getNode()).addChild(project); /// da li ovo treba
            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());*/
        /*}
        else if(myTreeNode.getNode() instanceof Project)
        {
            if(myTreeNode.getNode().getParent()==null)return;
            /*Presentation presentation = new Presentation(myTreeNode.getNode(),"Mihail","Prezentacija "+(myTreeNode.getChildCount()+1),"src/rudok/images/pozadina.jpg");
            MyTreeNode novi = new MyTreeNode(presentation, RuNodeType.PRESENTATION);
            PresentationView presentationView = new PresentationView(presentation);
            //presentation.addSubscriber(presentationView); // dali treba ovo - dodaje se u kontruktoru PresentationView-a
            myTreeNode.add(novi);
            ((Project) myTreeNode.getNode()).addChild(presentation); /// da li ovo treba pokrece i notify
            //MainFrame.getInstance().getProjectView().update(presentationView);
            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
            //SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());*/

        /*}
        else if(myTreeNode.getNode() instanceof Presentation)
        {
            /*Slide slide = new Slide(myTreeNode.getChildCount()+1, myTreeNode.getNode());
            MyTreeNode novi = new MyTreeNode(slide, RuNodeType.SLIDE);
            SlideView slideView = new SlideView(slide, new Dimension(250,150));
            SlideView previewSlideView = new SlideView(slide,new Dimension(100,100));
            SlideView slideshowSlideView = new SlideView(slide,new Dimension(400,200));
            myTreeNode.add(novi);
            ((Presentation) myTreeNode.getNode()).addChild(slide); /// da li ovo treba
            MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());*/
        //}
    }
}
