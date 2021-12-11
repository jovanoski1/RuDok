package rudok.actions;

import rudok.errors.ErrorFactory;
import rudok.gui.tree.model.MyTreeNode;
import rudok.model.workspace.Presentation;
import rudok.view.MainFrame;
import rudok.view.popup.EditAutorDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditAutorAction extends AbstractRudokAction{

    public EditAutorAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/editAutorIcon.png"));
        putValue(NAME,"Edit autor");
        putValue(SHORT_DESCRIPTION,"Edit autor");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p= MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(p==null) return;
        MyTreeNode myTreeNode = (MyTreeNode)p;
        if(myTreeNode.getNode() instanceof Presentation){
            EditAutorDialog editAutorDialog = new EditAutorDialog((Presentation) myTreeNode.getNode());
            return;
        }
        ErrorFactory.getInsance().createError("wrongSelected");
    }
}
