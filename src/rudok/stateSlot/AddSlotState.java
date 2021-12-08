package rudok.stateSlot;

import rudok.model.workspace.Slide;
import rudok.model.workspace.Slot;

public class AddSlotState implements SlotState{

    @Override
    public void slotAction(Slide slide, int x,int y){
        Slot slot = new Slot(x,y);
        slide.addSlot(slot);
        System.out.println("ADD SLOT MODE");
    }
}
