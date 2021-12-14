package rudok.gui.tree.view;

import rudok.model.workspace.Slot;
import rudok.observer.IPublisher;
import rudok.observer.ISubscriber;
import rudok.stateSlot.SelectSlotState;
import rudok.view.MainFrame;

import java.awt.*;

public class SlotView implements ISubscriber {
    Slot model;
    SlideView slideView;

    public SlotView(Slot model,SlideView slideView) {
        this.model = model;
        model.addSubscriber(this);
        this.slideView = slideView;
    }

    public void paint(Graphics2D g, int dx,int dy){
        if(model.isSelected()){
            g.setPaint(Color.CYAN);
        }
        else
            g.setPaint(model.getColor());
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        g.setStroke(model.getStroke());
        g.drawRect((int) (model.getX()*model.getxScale()*dx), (int) (model.getY()* model.getyScale()*dy), (int) (model.getWidth()*model.getxScale()*dx), (int) (model.getHeight()* model.getyScale()*dy));
        //g.fillRect((int) (model.getX()*model.getxScale()*dx), (int) (model.getY()* model.getyScale()*dy), (int) (model.getWidth()*model.getxScale()*dx), (int) (model.getHeight()* model.getyScale()*dy));
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
}
