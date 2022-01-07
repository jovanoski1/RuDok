package rudok.actions;

import rudok.Utils;
import rudok.gui.tree.model.MyTreeNode;
import rudok.model.workspace.Presentation;
import rudok.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SavePresentationAction extends AbstractRudokAction{

    public SavePresentationAction() {

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons/save_project.png"));
        putValue(NAME, "Save presentation");
        putValue(SHORT_DESCRIPTION, "Save presentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return (f.isDirectory() ||
                        f.getName().toLowerCase().endsWith(".gpf"));
            }

            @Override
            public String getDescription() {
                return "RuDok Presentation Files (*.gpf)";
            }
        });

        Object p= MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(p==null) return;
        MyTreeNode myTreeNode = (MyTreeNode)p;
        if(!(myTreeNode.getNode() instanceof Presentation)){
            return;
        }
        Presentation presentation = (Presentation) myTreeNode.getNode();
        File file;
        if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
            file=jfc.getSelectedFile();

        }else{
            return;
        }
        Utils.savePresentation(presentation,file);
        System.out.println(file);
    }
}
