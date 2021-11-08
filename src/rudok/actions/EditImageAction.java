package rudok.actions;

import rudok.errors.ErrorFactory;
import rudok.gui.tree.model.MyTreeNode;
import rudok.model.workspace.Presentation;
import rudok.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class EditImageAction extends AbstractRudokAction{

    public EditImageAction()
    {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON,loadIcon("icons/edit-image.png"));
        putValue(NAME,"Edit image");
        putValue(SHORT_DESCRIPTION,"Edit image");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object p= MainFrame.getInstance().getMyTree().getLastSelectedPathComponent();
        if(p==null) return;
        MyTreeNode myTreeNode = (MyTreeNode)p;
        if(myTreeNode.getNode() instanceof Presentation){
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
            jFileChooser.setAcceptAllFileFilterUsed(true);
            jFileChooser.showOpenDialog(null);
            File file = jFileChooser.getSelectedFile();
            if(file  == null)return;
            ((Presentation)myTreeNode.getNode()).setSlika(file.getPath());
//            System.out.println(file.getPath());
        }
        else{
            ErrorFactory.getInsance().createError("wrongSelected",MainFrame.getInstance());
        }
    }
}
