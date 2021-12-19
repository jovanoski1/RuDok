package rudok.gui.tree.controller;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class WorkspaceTreeCellRenderer extends DefaultTreeCellRenderer {

    public WorkspaceTreeCellRenderer(){

    }

    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        //System.out.println(node.getDepth() + " " + node.getLevel() + " " +node.getParent());
        if(node.getLevel() == 0){
            URL imageURL = getClass().getResource("images/workspace.png");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);
        }
        else if (node.getLevel() == 1) {
            URL imageURL = getClass().getResource("images/folder.png");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

        } else if (node.getLevel() == 2) {
            URL imageURL = getClass().getResource("images/presentation.png");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);
        }
        else  {
            URL imageURL = getClass().getResource("images/slides.png");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);
        }
        return this;
    }

}
