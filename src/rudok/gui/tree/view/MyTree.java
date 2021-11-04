package rudok.gui.tree.view;

import rudok.gui.tree.controller.WorkspaceTreeCellEditor;
import rudok.gui.tree.controller.WorkspaceTreeCellRenderer;
import rudok.gui.tree.controller.WorkspaceTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MyTree extends JTree {

    public MyTree() {

        addTreeSelectionListener(new WorkspaceTreeSelectionListener());
        setCellEditor(new WorkspaceTreeCellEditor(this,new DefaultTreeCellRenderer()));
        setCellRenderer(new WorkspaceTreeCellRenderer());
        setEditable(true);
    }

}
