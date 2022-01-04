package rudok.slotContentHandle;

import rudok.gui.tree.view.SlotView;
import rudok.model.workspace.Slot;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageSlotHandler implements ISlotHandler{
    @Override
    public String readContent(Slot slot) {
        return slot.getContent();
    }

    @Override
    public void setContent(Slot slot, String content) {
        slot.setContent(content);
    }

    @Override
    public void paint(SlotView slotView, Graphics2D g, int dx, int dy) {
        if(slotView.getModel().getContent().equals(""))return;
        try {
            final BufferedImage image = ImageIO.read(new File(slotView.getModel().getContent()));
            g.drawImage(image,(int) (slotView.getModel().getX()*slotView.getModel().getxScale()*dx), (int) (slotView.getModel().getY()* slotView.getModel().getyScale()*dy),
                    (int) (slotView.getModel().getWidth()*slotView.getModel().getxScale()*dx), (int) (slotView.getModel().getHeight()* slotView.getModel().getyScale()*dy),null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void format() {

    }
}
