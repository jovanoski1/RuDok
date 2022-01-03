package rudok.factory;

import rudok.gui.tree.controller.SlideMouseListener;
import rudok.gui.tree.view.SlideView;
import rudok.gui.tree.view.SlideViewType;
import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Slide;
import java.awt.*;

public class SlideFactory extends AbstractNodeFactory{
    @Override
    public RuNode createNode(RuNode ruNode) {
        Slide slide = new Slide((((Presentation)ruNode).getChildern().size()+1), ruNode);
        SlideView slideView = new SlideView(slide, new Dimension(250,150), SlideViewType.EDIT);
        SlideMouseListener slideMouseListener = new SlideMouseListener();
        slideView.addMouseListener(slideMouseListener);
        slideView.addMouseMotionListener(slideMouseListener);
        SlideView previewSlideView = new SlideView(slide,new Dimension(100,100),SlideViewType.PREVIEW);
        SlideView slideshowSlideView = new SlideView(slide,new Dimension(400,200), SlideViewType.SLIDESHOW);
        ((Presentation) ruNode).addChild(slide); /// da li ovo treba
        return slide;
    }
}
