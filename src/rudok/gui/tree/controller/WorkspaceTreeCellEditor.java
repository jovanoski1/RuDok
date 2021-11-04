package rudok.gui.tree.controller;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.model.workspace.Slide;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class WorkspaceTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private JTextField edit=null;
    private Object stavka=null;

    public WorkspaceTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {

        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        stavka=arg1;
        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (((MyTreeNode)stavka).getNode() instanceof Project) {
            ((MyTreeNode) stavka).getNode().setIme(e.getActionCommand());
        } else if (((MyTreeNode)stavka).getNode() instanceof Presentation) {
            ((MyTreeNode) stavka).getNode().setIme(e.getActionCommand());
        }else if(((MyTreeNode)stavka).getNode() instanceof Slide){
            ((MyTreeNode) stavka).getNode().setIme(e.getActionCommand());
        }
        
    }
}
