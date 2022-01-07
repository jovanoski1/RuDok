package rudok.actions;

import rudok.Utils;
import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNode;
import rudok.model.workspace.Project;
import rudok.model.workspace.Workspace;
import rudok.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class SaveWorkspaceAction extends AbstractRudokAction{

    public SaveWorkspaceAction() {

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons/save_project.png"));
        putValue(NAME, "Save workspace");
        putValue(SHORT_DESCRIPTION, "Save workspace");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.addChoosableFileFilter(new FileNameExtensionFilter("Workspace", "txt"));
        jfc.setAcceptAllFileFilterUsed(false);

        Object p= MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(p==null) return;
        MyTreeNode myTreeNode = (MyTreeNode)p;
        if(!(myTreeNode.getNode() instanceof Workspace)){
            return;
        }

        File file;
        if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
            file=jfc.getSelectedFile();

        }else{
            return;
        }

        try {
            Writer fileWriter = new FileWriter(file.getPath()+".txt", false);
            Workspace workspace = (Workspace) myTreeNode.getNode();
            for(RuNode ruNode : workspace.getChildern()){
                Project project = (Project) ruNode;
                System.out.println(project.getIme() + " "+project.getProjectFile());
                if(project.getProjectFile()==null || project.isChanged())continue;
                Utils.saveProject(project,project.getProjectFile());
                fileWriter.append(project.getProjectFile().getPath());
                fileWriter.append("\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
