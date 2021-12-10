package rudok.gui.tree.view;

import rudok.model.workspace.Slot;
import rudok.observer.IPublisher;
import rudok.observer.ISubscriber;

import java.awt.*;

public class SlotView implements ISubscriber {
    Slot model;

    public SlotView(Slot model) {
        this.model = model;
        model.addSubscriber(this);
    }

    public void paint(Graphics2D g, int dx,int dy){
        g.setPaint(model.getColor());
        g.fillRect((int) (model.getX()*model.getxScale()*dx), (int) (model.getY()* model.getyScale()*dy), (int) (model.getWidth()*model.getxScale()*dx), (int) (model.getHeight()* model.getyScale()*dy));
    }
    public boolean elementAt(int x,int y){
        return (x >= model.getX() && x <= model.getX() + model.getWidth()) && (y >= model.getY() && y < model.getY() + model.getHeight());
    }

    @Override
    public void update(Object notification) {

    }

    public Slot getModel() {
        return model;
    }
}
