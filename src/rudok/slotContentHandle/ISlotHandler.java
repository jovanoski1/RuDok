package rudok.slotContentHandle;

import rudok.gui.tree.view.SlotView;
import rudok.model.workspace.Slot;

import java.awt.*;

public interface ISlotHandler {
    String readContent(Slot slot);
    void setContent(Slot slot, String content);
    void paint(SlotView slotView, Graphics2D g, int dx, int dy);
    void format();
}
