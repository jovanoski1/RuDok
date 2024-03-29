package rudok.stateSlot;

import rudok.gui.tree.view.SlideView;
import rudok.gui.tree.view.SlotView;

public class DeleteSlotState implements SlotState{

    @Override
    public void slotAction(SlideView slideView, int x, int y) {
        for(SlotView sv: slideView.getSlotViewList()){
            if(sv.elementAt(x,y)){
                slideView.getModel().removeSlot(sv.getModel());
                break;
            }
        }
        System.out.println("DELETE SLOT MODE");
    }

    @Override
    public void moveSlot(SlideView slideView,int x, int y) {

    }
}
