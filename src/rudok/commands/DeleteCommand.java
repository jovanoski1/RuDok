package rudok.commands;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;
import rudok.view.MainFrame;

import javax.swing.*;

public class DeleteCommand extends AbstractCommand{

    MyTreeNode parent;
    MyTreeNode node;
    int index;

    public DeleteCommand(MyTreeNode parent, MyTreeNode node,int index) {
        this.parent = parent;
        this.node = node;
        this.index = index;
    }

    @Override
    public void doCommand() {
        parent.remove(node);
        ((RuNodeComposite) parent.getNode()).removeChild(node.getNode());

        if(node.getNode().equals(MainFrame.getInstance().getProjectView().getModel())){
            MainFrame.getInstance().getProjectView().removeAll();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
        }
    }

    @Override
    public void undoCommand() {
        parent.insert(node,index);
        ((RuNodeComposite) parent.getNode()).addChild(node.getNode());
        (node.getNode()).setParent(parent.getNode());

    }
}
