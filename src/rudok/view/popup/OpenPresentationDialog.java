package rudok.view.popup;

import rudok.commands.ShareCommand;
import rudok.gui.tree.controller.SlideMouseListener;
import rudok.gui.tree.model.MyTreeNode;
import rudok.gui.tree.view.PresentationView;
import rudok.gui.tree.view.SlideView;
import rudok.gui.tree.view.SlideViewType;
import rudok.gui.tree.view.SlotView;
import rudok.model.tree.RuNode;
import rudok.model.workspace.*;
import rudok.slotContentHandle.ISlotHandler;
import rudok.slotContentHandle.ImageSlotHandler;
import rudok.slotContentHandle.TextSlotHandler;
import rudok.view.MainFrame;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class OpenPresentationDialog extends JDialog {

    private JComboBox<MyTreeNode> jComboBox = new JComboBox<>();
    private JButton okButton;
    private JButton openButton;
    private JLabel jLabel;
    private File file;
    public OpenPresentationDialog() {
        this.setTitle("Share Presentation");
        gui();
        controllers();
        this.setLocationRelativeTo(MainFrame.getInstance());
        this.setSize(300,200);
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    private void gui() {
        MyTreeNode workspace = (MyTreeNode) MainFrame.getInstance().getMyTree().getModel().getRoot();
        for (int i = 0; i < workspace.getChildCount(); i++) {
            MyTreeNode project = (MyTreeNode) workspace.getChildAt(i);
            jComboBox.addItem(project);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        okButton = new JButton("Import");
        openButton = new JButton("Open");
        jLabel = new JLabel("");
        panel.add(jLabel);
        panel.add(openButton);


        panel.add(jComboBox);


        panel.add(okButton);
        this.add(panel);

    }
    private void controllers(){
        openButton.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return (f.isDirectory() ||
                            f.getName().toLowerCase().endsWith(".gpfp"));
                }

                @Override
                public String getDescription() {
                    return "GrafEditor Project Files (*.gpfp)";
                }
            });
            if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                jLabel.setText(jfc.getSelectedFile().getPath());
                file = jfc.getSelectedFile();
            }
        });


        okButton.addActionListener(e -> {
            if(file == null)return;
            MyTreeNode projectMTN = (MyTreeNode) jComboBox.getSelectedItem();
            Project project = (Project) projectMTN.getNode();
            ObjectInputStream os = null;
            try {
                os = new ObjectInputStream(new
                        FileInputStream(file));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Presentation presentation=null;
            try {
                assert os != null;
                presentation = (Presentation) os.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }


            MyTreeNode presentationMTN = new MyTreeNode(presentation,RuNodeType.PRESENTATION);
            for(RuNode ruNode1:presentation.getChildern()){
                Slide slide = (Slide) ruNode1;
                MyTreeNode slideMTN = new MyTreeNode(slide, RuNodeType.SLIDE);
                SlideView slideView = new SlideView(slide, new Dimension(250,150), SlideViewType.EDIT);
                SlideMouseListener slideMouseListener = new SlideMouseListener();
                slideView.addMouseListener(slideMouseListener);
                slideView.addMouseMotionListener(slideMouseListener);
                SlideView previewSlideView = new SlideView(slide,new Dimension(100,100),SlideViewType.PREVIEW);
                SlideView slideshowSlideView = new SlideView(slide,new Dimension(400,200), SlideViewType.SLIDESHOW);

                for(Slot slot:slide.getSlots()){
                    slot.setSelected(false);
                    ISlotHandler slotHandler;
                    if(slot.getType().equals(SlotType.TEXT))slotHandler = new TextSlotHandler();
                    else slotHandler = new ImageSlotHandler();
                    SlotView slotView1 = new SlotView(slot,slideView,slotHandler);
                    slideView.addSlotView(slotView1);
                    SlotView slotView2 = new SlotView(slot,previewSlideView,slotHandler);
                    previewSlideView.addSlotView(slotView2);
                    SlotView slotView3 = new SlotView(slot,slideshowSlideView,slotHandler);
                    slideshowSlideView.addSlotView(slotView3);
                }

                presentationMTN.add(slideMTN);
            }
            PresentationView presentationView = new PresentationView(presentation);
            projectMTN.add(presentationMTN);
            project.addChild(presentation);
            presentation.setParent(project);

            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
            this.dispose();
        });
    }
}
