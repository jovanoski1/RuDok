package rudok.gui.tree.controller;

import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.model.workspace.Slide;

import javax.swing.*;
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


        if (value instanceof Project) {
            URL imageURL = getClass().getResource("images/tdiagram.gif");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);

        } else if (value instanceof Presentation) {
            URL imageURL = getClass().getResource("images/tproject.gif");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);
        }
        else if (value instanceof Slide) {
            URL imageURL = getClass().getResource("images/tproject.gif");
            Icon icon = null;
            if (imageURL != null)
                icon = new ImageIcon(imageURL);
            setIcon(icon);
        }


        return this;
    }

}
