package rudok.gui.tree.view;

import rudok.model.workspace.Slot;
import rudok.observer.ISubscriber;
import rudok.slotContentHandle.ISlotHandler;

import java.awt.*;

public class SlotView implements ISubscriber {
    Slot model;
    SlideView slideView;
    ISlotHandler slotHandler;

    public SlotView(Slot model,SlideView slideView, ISlotHandler slotHandler) {
        this.model = model;
        model.addSubscriber(this);
        this.slideView = slideView;
        this.slotHandler = slotHandler;
    }

    public void paint(Graphics2D g, int dx,int dy){
        if(model.isSelected() && !slideView.getType().equals(SlideViewType.SLIDESHOW)){
            g.setPaint(Color.CYAN);
        }
        else
            g.setPaint(model.getColor());
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g.setStroke(model.getStroke());
        g.drawRect((int) (model.getX()*model.getxScale()*dx), (int) (model.getY()* model.getyScale()*dy), (int) (model.getWidth()*model.getxScale()*dx), (int) (model.getHeight()* model.getyScale()*dy));
        if(slideView.getType().equals(SlideViewType.SLIDESHOW)){
            g.setPaint(Color.white);
            slotHandler.paint(this,g, dx, dy);
        }
    }
    public boolean elementAt(int x,int y){
        return (x >= model.getX() && x <= model.getX() + model.getWidth()) && (y >= model.getY() && y < model.getY() + model.getHeight());
    }

    @Override
    public void update(Object notification) {
        slideView.repaint();
    }

    public Slot getModel() {
        return model;
    }

    public SlideView getSlideView() {
        return slideView;
    }

    public ISlotHandler getSlotHandler() {
        return slotHandler;
    }
}
