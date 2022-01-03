package rudok.slotContentHandle;

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
    public void paint(Slot slot, Graphics2D g, int dx,int dy) {
        if(slot.getContent().equals(""))return;
        try {
            final BufferedImage image = ImageIO.read(new File(slot.getContent()));
            g.drawImage(image,(int) (slot.getX()*slot.getxScale()*dx), (int) (slot.getY()* slot.getyScale()*dy), (int) (slot.getWidth()*slot.getxScale()*dx), (int) (slot.getHeight()* slot.getyScale()*dy),null);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void format() {

    }
}
