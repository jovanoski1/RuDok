package rudok.actions;

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
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class OpenProjectAction extends AbstractRudokAction{

    public OpenProjectAction() {

        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons/open_project.png"));
        putValue(NAME, "Save project");
        putValue(SHORT_DESCRIPTION, "Save project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
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
        if(jfc.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION) {

            ObjectInputStream os = null;
            try {
                os = new ObjectInputStream(new
                        FileInputStream(jfc.getSelectedFile()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Project p = null;
            try {
            //poziv metode readObject() koja vrši dubinsku deserijalizaciju
                assert os != null;
                p = (Project) os.readObject();
            } catch (ClassNotFoundException | IOException ez) {
                ez.printStackTrace();
            }
            MyTreeNode projectMTN = new MyTreeNode(p, RuNodeType.PROJECT);
            MyTreeNode workspaceMTN = (MyTreeNode) MainFrame.getInstance().getMyTree().getModel().getRoot();
            workspaceMTN.add(projectMTN);

            for(RuNode ruNode:p.getChildern()){
                Presentation presentation = (Presentation) ruNode;
                MyTreeNode presentationMTN = new MyTreeNode(presentation,RuNodeType.PRESENTATION);

                for(RuNode ruNode1:presentation.getChildern()){
                    Slide slide = (Slide) ruNode1;
                    MyTreeNode slideMTN = new MyTreeNode(slide,RuNodeType.SLIDE);
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
            }
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMyTree());
        }
    }
}
