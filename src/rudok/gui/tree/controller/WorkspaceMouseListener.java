package rudok.gui.tree.controller;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.workspace.Project;
import rudok.view.MainFrame;
import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WorkspaceMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2)
        {
            int selRow = MainFrame.getInstance().getMyTree().getRowForLocation(e.getX(), e.getY());
            TreePath selPath = MainFrame.getInstance().getMyTree().getPathForLocation(e.getX(), e.getY());
            if(selPath==null)return;
            if(((MyTreeNode)selPath.getLastPathComponent()).getNode() instanceof Project)
            {

                Project project = (Project) ((MyTreeNode)selPath.getLastPathComponent()).getNode();
                MainFrame.getInstance().getProjectView().setModel(project);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
