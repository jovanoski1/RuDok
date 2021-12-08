package rudok.stateSlot;

import rudok.gui.tree.view.SlotView;
import rudok.model.workspace.Slide;
import rudok.model.workspace.Slot;

public class DeleteSlotState implements SlotState{

    @Override
    public void slotAction(Slide slide, int x, int y) {
        for(Slot sv:slide.getSlots()){
            if(((SlotView)sv.getSubscribers().get(0)).elementAt(x,y)){
                slide.removeSlot(sv);
                break;
            }
        }
        System.out.println("DELETE SLOT MODE");
    }
}
