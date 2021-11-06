package rudok.actions;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;
import rudok.model.workspace.Workspace;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractRudokAction{

    public DeleteAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/remove.png"));
        putValue(NAME,"Delete");
        putValue(SHORT_DESCRIPTION,"Delete");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object p= MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(p==null) return;
        MyTreeNode myTreeNode = (MyTreeNode)p;
        if(myTreeNode.getNode() instanceof Workspace)return;
        myTreeNode.removeFromParent();

        ((RuNodeComposite) myTreeNode.getNode().getParent()).removeChild(myTreeNode.getNode());
        //((RuNodeComposite)myTreeNode.getNode().getParent()).removeChild((RuNodeComposite)myTreeNode.getNode());
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }
}
