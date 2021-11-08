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
        model.getParent().addSubscriber(this);
        gui();
    }
    private void gui(){
        this.removeAll();
        SpringLayout springLayout = new SpringLayout();
        Container cont = this;
        cont.setLayout(springLayout);

        JLabel label = new JLabel(String.valueOf(model.getRedniBroj()));
        label.setForeground(Color.WHITE);
        springLayout.putConstraint(SpringLayout.WEST, label, 20, SpringLayout.WEST, cont);
        springLayout.putConstraint(SpringLayout.SOUTH, label, -10, SpringLayout.SOUTH, cont);
        cont.add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String url = ((Presentation)model.getParent()).getSlika();
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(url));
        } catch (IOException e) {
        }
        g.drawImage(img,0,0,getWidth(),getHeight(),null);
    }

    @Override
    public void update(Object notification) {
        gui();
    }
}
