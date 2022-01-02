package rudok.actions;

import rudok.commands.AddCommand;
import rudok.factory.AbstractNodeFactory;
import rudok.factory.NodeFactoryChooser;
import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNodeComposite;
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
        MyTreeNode child = new MyTreeNode(f.getNewRuNode(myTreeNode.getNode()),RuNodeType.values()[ind+1]);
        myTreeNode.add(child);
        MainFrame.getInstance().getCommandManager().addCommand(new AddCommand(myTreeNode, child));
        MainFrame.getInstance().getMyTree().expandPath(MainFrame.getInstance().getMyTree().getSelectionPath());
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
    }
}
