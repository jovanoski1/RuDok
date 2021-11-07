package rudok.gui.tree.view;

import rudok.model.workspace.Presentation;
import rudok.model.workspace.Slide;
import rudok.observer.ISubscriber;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SlideView extends JPanel implements ISubscriber {

    private Slide model;

    public SlideView(Slide model){
        this.model=model;
        model.addSubscriber(this);
        //this.add(new JLabel(model.getIme()));
        this.setMinimumSize(new Dimension(200,200));
        this.setPreferredSize(new Dimension(200,200));

        JLabel pic = new JLabel(new ImageIcon(((Presentation)model.getParent()).getSlika()));
        this.add(pic);
    }


    @Override
    public void update(Object notification) {

    }
}
