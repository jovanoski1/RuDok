package rudok.view.popup;

import rudok.commands.ShareCommand;
import rudok.gui.tree.model.MyTreeNode;
import rudok.view.MainFrame;
import javax.swing.*;
import java.awt.*;

public class SharePresentationDialog extends JDialog {

    private MyTreeNode myTreeNode;

    private JComboBox<MyTreeNode> jComboBox = new JComboBox<>();

    public SharePresentationDialog(MyTreeNode myTreeNode) {
        this.myTreeNode = myTreeNode;
        this.setTitle("Share Presentation");
        gui();
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setSize(300,100);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    private void gui(){
        MyTreeNode workspace = (MyTreeNode) myTreeNode.getParent().getParent();
        for(int i=0;i<workspace.getChildCount();i++){
            MyTreeNode project = (MyTreeNode) workspace.getChildAt(i);
            boolean contains = false;
            for(int j=0;j<project.getChildCount();j++){
                MyTreeNode presentation = (MyTreeNode) project.getChildAt(j);
                if(presentation.getNode().equals(myTreeNode.getNode())){
                    contains = true;
                    break;
                }
            }
            if(!contains){
                jComboBox.addItem(project);
            }
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(jComboBox);

        JButton okButton  = new JButton("Share");
        panel.add(okButton);
        this.add(panel);
        okButton.addActionListener(e -> {
            MyTreeNode newParent = (MyTreeNode) jComboBox.getSelectedItem();
            /*myTreeNode.getNode().setIme(myTreeNode.getNode().getIme() + " - Shared");
            MyTreeNode toShare = new MyTreeNode(myTreeNode.getNode(),myTreeNode.getType());
            Presentation presentation = (Presentation) myTreeNode.getNode();
            Project project = (Project) newParent.getNode();

            newParent.add(toShare);
            project.addChild(presentation);
            presentation.getSharedTo().add(project);*/
            MainFrame.getInstance().getCommandManager().addCommand(new ShareCommand(newParent,new MyTreeNode(myTreeNode.getNode(),myTreeNode.getType()),myTreeNode.getNode().getIme()));

            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
            this.dispose();
        });
    }

}
