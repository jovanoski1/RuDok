package rudok.stateSlot;

import rudok.gui.tree.view.SlideView;
import rudok.model.workspace.Slide;
import rudok.model.workspace.Slot;

public class AddSlotState implements SlotState{

    @Override
    public void slotAction(SlideView slideView, int x, int y){
        Slot slot = new Slot(x,y,1.0/slideView.getWidth(),1.0/slideView.getHeight());
        slideView.getModel().addSlot(slot);
        System.out.println("ADD SLOT MODE");
    }
}
