package rudok;

import rudok.commands.AddCommand;
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
import java.awt.*;
import java.io.*;

public class Utils {
    private Utils(){}

    private static final String lastWorkspacePath = "workspace.txt";

    public static void openProject(String line){
        ObjectInputStream os = null;
        try {
            os = new ObjectInputStream(new
                    FileInputStream(line));
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
        if (p==null)return;
        p.setProjectFile(new File(line));
        p.setChanged(false);
        p.addSubscriber(MainFrame.getInstance().getProjectView());
        MyTreeNode projectMTN = new MyTreeNode(p, RuNodeType.PROJECT);
        MyTreeNode workspaceMTN = (MyTreeNode) MainFrame.getInstance().getMyTree().getModel().getRoot();
        workspaceMTN.add(projectMTN);
        ((Workspace)workspaceMTN.getNode()).addChild(p);
        p.setParent((Workspace)workspaceMTN.getNode());

        //MainFrame.getInstance().getCommandManager().addCommand(new AddCommand(workspaceMTN,projectMTN));
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

    public static void saveProject(Project project, File projectFile){

        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(projectFile));
            os.writeObject(project);
            project.setProjectFile(projectFile);
            project.setChanged(false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static String getLastWorkspacePath() {
        return lastWorkspacePath;
    }
}
