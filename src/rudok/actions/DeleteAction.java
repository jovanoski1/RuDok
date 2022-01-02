package rudok.actions;

import jdk.swing.interop.SwingInterOpUtils;
import rudok.commands.AddCommand;
import rudok.commands.DeleteCommand;
import rudok.errors.ErrorFactory;
import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;
import rudok.model.workspace.Project;
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
        if(myTreeNode.getNode() instanceof Workspace){
            ErrorFactory.getInsance().createError("workspaceDelete");
            return;
        }
        if(myTreeNode.getNode().getParent() == null)return;
        MainFrame.getInstance().getCommandManager().addCommand(new DeleteCommand((MyTreeNode) myTreeNode.getParent(), myTreeNode,myTreeNode.getParent().getIndex(myTreeNode)));
        myTreeNode.removeFromParent();
        ((RuNodeComposite) myTreeNode.getNode().getParent()).removeChild(myTreeNode.getNode());
        if(myTreeNode.getNode().equals(MainFrame.getInstance().getProjectView().getModel())){
            MainFrame.getInstance().getProjectView().removeAll();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getSplit().getRightComponent());
        }
        //MainFrame.getInstance().getMyTree().clearSelection();
        myTreeNode.getNode().setParent(null);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }
}
