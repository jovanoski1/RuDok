package rudok.gui.tree.view;

import rudok.model.workspace.*;
import rudok.observer.ISubscriber;
import rudok.slotContentHandle.ISlotHandler;
import rudok.slotContentHandle.ImageSlotHandler;
import rudok.slotContentHandle.TextSlotHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlideView extends JPanel implements ISubscriber {

    private Slide model;
    private SlideViewType type;
    private List<SlotView> slotViewList = new ArrayList<>();

    public SlideView(Slide model, Dimension dimension ,SlideViewType type){
        this.model=model;
        this.type = type;
        model.addSubscriber(this);
        this.setPreferredSize(dimension);
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
            sv.paint((Graphics2D) g, this.getWidth(),this.getHeight());
        }
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof dummySlotNotification){
            dummySlotNotification n = (dummySlotNotification) notification;
            if(n.getStatus().equals("added")){
                ISlotHandler slotHandler;
                if(n.getSlot().getType().equals(SlotType.TEXT))slotHandler = new TextSlotHandler();
                else slotHandler = new ImageSlotHandler();
                slotViewList.add(new SlotView(n.getSlot(),this, slotHandler));
            }
            else{
                this.removeSlotView(n.getSlot());
            }
            repaint();
        }
        //gui();
    }

    private void removeSlotView(Slot s){
        for(SlotView sv:slotViewList){
            if(sv.getModel().equals(s)){
                slotViewList.remove(sv);
                break;
            }
        }
    }

    public void addSlotView(SlotView slotView){
        slotViewList.add(slotView);
    }

    public List<SlotView> getSlotViewList() {
        return slotViewList;
    }

    public void setSlotViewList(List<SlotView> slotViewList) {
        this.slotViewList = slotViewList;
    }

    public Slide getModel() {
        return model;
    }

    public SlideViewType getType() {
        return type;
    }
}
