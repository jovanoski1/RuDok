package rudok.commands;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNodeComposite;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.view.MainFrame;
import javax.swing.*;
import javax.swing.tree.MutableTreeNode;
import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand{

    private MyTreeNode parent;
    private MyTreeNode node;
    private int index;
    private List<MyTreeNode> projects = new ArrayList<>();
    private List<MyTreeNode> presentationNodes = new ArrayList<>();
    private List<Integer> indexes = new ArrayList<>();

    public DeleteCommand(MyTreeNode parent, MyTreeNode node,int index) {
        this.parent = parent;
        this.node = node;
        this.index = index;
    }

    @Override
    public void doCommand() {
        parent.remove(node);
        //((RuNodeComposite) parent.getNode()).removeChild(node.getNode());
        ((RuNodeComposite)node.getNode().getParent()).removeChild(node.getNode());
        if(node.getNode() instanceof Presentation){
            MyTreeNode workspace = (MyTreeNode) parent.getParent();
            for(int i=0;i<workspace.getChildCount();i++){
                MyTreeNode project = (MyTreeNode) workspace.getChildAt(i);
                for(int j=0;j<project.getChildCount();j++){
                    if(((MyTreeNode)project.getChildAt(j)).getNode().equals(node.getNode())){
                        projects.add(project);
                        presentationNodes.add((MyTreeNode) project.getChildAt(j));
                        indexes.add(project.getIndex(project.getChildAt(j)));
                        project.remove((MutableTreeNode) project.getChildAt(j));
                    }
                }
            }
            for(Project p:((Presentation) node.getNode()).getSharedTo()){
                p.removeSharedPresentation((Presentation) node.getNode());
            }
        }
        System.out.println(node.getNode().getParent().getIme());
        node.getNode().setParent(null);

        if(node.getNode().equals(MainFrame.getInstance().getProjectView().getModel())){
            MainFrame.getInstance().getProjectView().removeAll();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
        }
    }

    @Override
    public void undoCommand() {
        (node.getNode()).setParent(parent.getNode());
        parent.insert(node,index);
        ((RuNodeComposite) parent.getNode()).addChild(node.getNode());
        if(node.getNode() instanceof Presentation){
            for(int i=0;i<projects.size();i++){
                projects.get(i).insert(presentationNodes.get(i),indexes.get(i));
                ((Project)projects.get(i).getNode()).addSharedPresentation((Presentation) presentationNodes.get(i).getNode());
            }
            /*projects.clear();
            presentationNodes.clear();
            indexes.clear();*/
        }
    }
}
