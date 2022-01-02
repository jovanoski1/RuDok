package rudok.commands;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNodeComposite;
import rudok.view.MainFrame;

import javax.swing.*;

public class AddCommand extends AbstractCommand{

    MyTreeNode parent;
    MyTreeNode node;

    public AddCommand(MyTreeNode parent, MyTreeNode node) {
        this.parent = parent;
        this.node = node;
    }

    @Override
    public void doCommand() {
        (node.getNode()).setParent(parent.getNode());
        parent.add(node);
        ((RuNodeComposite) parent.getNode()).addChild(node.getNode());

    }

    @Override
    public void undoCommand() {
        parent.remove(node);
        ((RuNodeComposite) parent.getNode()).removeChild(node.getNode());
        parent.getNode().setParent(null);
        if(node.getNode().equals(MainFrame.getInstance().getProjectView().getModel())){
            MainFrame.getInstance().getProjectView().removeAll();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
        }
    }
}
