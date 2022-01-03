package rudok.slotContentHandle;

import rudok.model.workspace.Slot;

import java.awt.*;

public class TextSlotHandler implements ISlotHandler{

    @Override
    public String readContent(Slot slot) {
        return slot.getContent();
    }

    @Override
    public void setContent(Slot slot, String content) {
        slot.setContent(content);
    }

    @Override
    public void paint(Slot slot,Graphics2D g, int dx,int dy) {
        g.drawString(slot.getContent(), (int) (slot.getX()*slot.getxScale()*dx), (int) (slot.getY()* slot.getyScale()*dy)+10);
    }

    @Override
    public void format() {

    }
}
