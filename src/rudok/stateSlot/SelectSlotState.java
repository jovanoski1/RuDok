package rudok.stateSlot;

import rudok.gui.tree.view.SlideView;
import rudok.gui.tree.view.SlotView;
import rudok.model.workspace.Slot;

public class SelectSlotState implements SlotState{
    Slot selectedSlot=null;
    @Override
    public void slotAction(SlideView slideView, int x, int y) {
        boolean someIsSelected=false;
        for(SlotView sv: slideView.getSlotViewList()){
            if(sv.elementAt(x,y)){
                if(sv.getModel().isSelected()){
                    sv.getModel().setSelected(false);
                    selectedSlot = null;
                }
                else{
                    sv.getModel().setSelected(true);
                    if(selectedSlot != null)selectedSlot.setSelected(false);
                    selectedSlot = sv.getModel();
                }
                someIsSelected=true;
                break;
            }
        }
        if(!someIsSelected){
            for(SlotView sv: slideView.getSlotViewList()){
                sv.getModel().setSelected(false);
            }
        }
    }

    @Override
    public void moveSlot(SlideView slideView, int x, int y) {

    }
}
