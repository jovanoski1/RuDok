package rudok.stateSlot;

import rudok.gui.tree.view.SlideView;
import rudok.model.workspace.Slide;

public interface SlotState {
    void slotAction(SlideView slideView, int x, int y);
}
