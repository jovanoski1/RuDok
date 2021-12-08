package rudok.gui.tree.view;

import rudok.model.workspace.Presentation;
import rudok.model.workspace.Slide;
import rudok.model.workspace.Slot;
import rudok.model.workspace.dummySlotNotification;
import rudok.observer.ISubscriber;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SlideView extends JPanel implements ISubscriber {

    private Slide model;
    private List<SlotView> slotViewList = new ArrayList<>();

    public SlideView(Slide model, Dimension dimension){
        this.model=model;
        model.addSubscriber(this);
        //this.add(new JLabel(model.getIme()));
        //this.setMinimumSize(dimension);//new Dimension(200,200));
        this.setPreferredSize(dimension);//new Dimension(200,200));
        //this.setMaximumSize(dimension);
        //model.getParent().addSubscriber(this);
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

        for(SlotView sv:slotViewList){
            sv.paint((Graphics2D) g);
        }
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof dummySlotNotification){
            dummySlotNotification n = (dummySlotNotification) notification;
            if(n.getStatus().equals("added")){
                slotViewList.add(new SlotView(n.getSlot()));
            }
            else{
                this.removeSlotView(n.getSlot());
            }
            repaint();
        }
        gui();
    }

    private void removeSlotView(Slot s){
        for(SlotView sv:slotViewList){
            if(sv.getModel().equals(s)){
                slotViewList.remove(sv);
                break;
            }
        }
    }


    public Slide getModel() {
        return model;
    }
}
