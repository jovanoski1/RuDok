package rudok.stateSlot;

import rudok.gui.tree.view.SlideView;
import rudok.model.workspace.Slot;
import rudok.model.workspace.SlotType;

import java.awt.*;

public class AddSlotState implements SlotState{

    private Color color =Color.RED;
    private int width;
    private float lineType = 0.1f;
    private SlotType type = SlotType.TEXT;

    @Override
    public void slotAction(SlideView slideView, int x, int y){
        Slot slot = new Slot(x,y,1.0/slideView.getWidth(),1.0/slideView.getHeight(), color,type,slideView.getModel());
        slot.setStroke(new BasicStroke(width,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,10, new float[]{(float) lineType},0.0f));
        slideView.getModel().addSlot(slot);
        System.out.println("ADD SLOT MODE");
    }

    @Override
    public void moveSlot(SlideView slideView,int x, int y) {

    }

    public void setType(SlotType type) {
        this.type = type;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLineType(float line) {
        this.lineType = line;
    }
}
