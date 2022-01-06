package rudok.commands;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNodeComposite;
import rudok.model.workspace.Presentation;
import rudok.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AddCommand extends AbstractCommand{

    private MyTreeNode parent;
    private MyTreeNode node;
    private List<MyTreeNode> slides = new ArrayList<>();

    public AddCommand(MyTreeNode parent, MyTreeNode node) {
        this.parent = parent;
        this.node = node;
    }

    @Override
    public void doCommand() {
        (node.getNode()).setParent(parent.getNode());
        //parent.add(node);
        ((RuNodeComposite) parent.getNode()).addChild(node.getNode());

        /**
         create MyTreeNode for slide and add to every Presentation shared
         */
        if(parent.getNode() instanceof Presentation){
            MyTreeNode workspace = (MyTreeNode) parent.getParent().getParent();
            for(int i=0;i<workspace.getChildCount();i++){
                MyTreeNode project = (MyTreeNode) workspace.getChildAt(i);
                for(int j=0;j<project.getChildCount();j++){
                    MyTreeNode presentation = (MyTreeNode) project.getChildAt(j);
                    if(presentation.getNode().equals(parent.getNode())){
                        MyTreeNode sharedSlide = new MyTreeNode(node.getNode(),node.getType());
                        presentation.add(sharedSlide);
                        slides.add(sharedSlide);
                    }
                }
            }
        }
        else parent.add(node);
    }

    @Override
    public void undoCommand() {
        //parent.remove(node);
        node.removeFromParent();
        ((RuNodeComposite) parent.getNode()).removeChild(node.getNode());

        /**  Delete MyTreeNode for slide in shared presentations            */
        if(parent.getNode() instanceof Presentation){
            System.out.println(slides.size());
            for(MyTreeNode mtn : slides){
                mtn.removeFromParent();
            }
            slides.clear();
        }

        node.getNode().setParent(null);
        if(node.getNode().equals(MainFrame.getInstance().getProjectView().getModel())){
            MainFrame.getInstance().getProjectView().removeAll();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
        }
    }
}
