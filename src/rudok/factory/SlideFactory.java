package rudok.factory;

import rudok.gui.tree.controller.SlideMouseListener;
import rudok.gui.tree.model.MyTreeNode;
import rudok.gui.tree.view.SlideView;
import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.RuNodeType;
import rudok.model.workspace.Slide;
import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class SlideFactory extends AbstractNodeFactory{
    @Override
    public RuNode createNode(MyTreeNode myTreeNode) {
        Slide slide = new Slide(myTreeNode.getChildCount()+1, myTreeNode.getNode());
        SlideView slideView = new SlideView(slide, new Dimension(250,150));
        slideView.addMouseListener(new SlideMouseListener());
        SlideView previewSlideView = new SlideView(slide,new Dimension(100,100));
        SlideView slideshowSlideView = new SlideView(slide,new Dimension(400,200));
        ((Presentation) myTreeNode.getNode()).addChild(slide); /// da li ovo treba
        return slide;
    }
}
