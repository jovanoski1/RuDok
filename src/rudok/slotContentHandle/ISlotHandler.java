package rudok.slotContentHandle;

import rudok.model.workspace.Slot;

import java.awt.*;

public interface ISlotHandler {
    String readContent(Slot slot);
    void setContent(Slot slot, String content);
    void paint(Slot slot, Graphics2D g, int dx,int dy);
    void format();
}
