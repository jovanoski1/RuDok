package rudok.actions;

import rudok.errors.ErrorFactory;
import rudok.gui.tree.model.MyTreeNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Workspace;
import rudok.view.MainFrame;
import rudok.view.popup.SharePresentationDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SharePresentationAction  extends AbstractRudokAction{
    public SharePresentationAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/share.png"));
        putValue(NAME,"SharePresentation");
        putValue(SHORT_DESCRIPTION,"Share presentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p= MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(p==null) return;
        MyTreeNode myTreeNode = (MyTreeNode)p;
        if(!(myTreeNode.getNode() instanceof Presentation)){
            ErrorFactory.getInsance().createError("wrongSelected");
            return;
        }
        SharePresentationDialog sharePresentationDialog = new SharePresentationDialog(myTreeNode);
    }
}
