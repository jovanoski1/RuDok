package rudok.stateSlot;

import rudok.model.workspace.Slide;

public interface SlotState {
    void slotAction(Slide slide, int x,int y);
}
