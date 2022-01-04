package rudok.slotContentHandle;

import rudok.gui.tree.view.SlotView;
import rudok.model.workspace.Slot;

import javax.swing.*;
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
    public void paint(SlotView slotView,Graphics2D g, int dx,int dy) {
        JLabel renderer = new JLabel(slotView.getModel().getContent());
        CellRendererPane crp = new CellRendererPane();
        //(slotView).getSlideView().add(crp);
        renderer.setForeground(Color.WHITE);
        crp.paintComponent(g, renderer, (slotView).getSlideView(),
                (int) (slotView.getModel().getX()*slotView.getModel().getxScale()*dx), (int) (slotView.getModel().getY()* slotView.getModel().getyScale()*dy)-10,
                (int) (slotView.getModel().getWidth()*slotView.getModel().getxScale()*dx), (int) (slotView.getModel().getHeight()* slotView.getModel().getyScale()*dy));
        //g.drawString(slot.getContent(), (int) (slot.getX()*slot.getxScale()*dx), (int) (slot.getY()* slot.getyScale()*dy)+10);
    }

    @Override
    public void format() {

    }
}
