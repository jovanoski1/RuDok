package rudok.actions;

import rudok.Utils;
import rudok.gui.tree.model.MyTreeNode;
import rudok.model.workspace.Project;
import rudok.view.MainFrame;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class SaveProjectAction extends AbstractRudokAction{

    public SaveProjectAction() {

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons/save_project.png"));
        putValue(NAME, "Save project");
        putValue(SHORT_DESCRIPTION, "Save project");
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
                return "GrafEditor Project Files (*.gpf)";
            }
        });

        Object p= MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(p==null) return;
        MyTreeNode myTreeNode = (MyTreeNode)p;
        if(!(myTreeNode.getNode() instanceof Project)){
            return;
        }

        Project project = (Project) myTreeNode.getNode();
        File projectFile=(project.getProjectFile());

        if (!project.isChanged()){
            return;
        }

        if (project.getProjectFile()==null){
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                projectFile=jfc.getSelectedFile();

            }else{
                return;
            }

        }
        Utils.saveProject(project, projectFile);

    }
}
