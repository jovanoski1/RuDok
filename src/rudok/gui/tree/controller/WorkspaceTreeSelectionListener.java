package rudok.gui.tree.controller;

import rudok.model.workspace.Presentation;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class WorkspaceTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        for(int i=0; i<path.getPathCount(); i++){
            if(path.getPathComponent(i) instanceof Presentation){
                Presentation d=(Presentation)path.getPathComponent(i);

                //selektovan je dijagram u stablu, potreno je pronaci odgovarajuci
                //DiagramView i postaviti ga u fokus
                System.out.println("Selektovan dijagram:"+d);
                System.out.println("getPath: "+e.getPath());
                System.out.println("getPath: "+e.getNewLeadSelectionPath());
                break;
            }
        }
    }
}
