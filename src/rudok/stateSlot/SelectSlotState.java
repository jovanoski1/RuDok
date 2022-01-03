package rudok.stateSlot;

import rudok.gui.tree.view.SlideView;
import rudok.gui.tree.view.SlotView;
import rudok.model.workspace.Slot;

public class SelectSlotState implements SlotState{
    SlotView selectedSlot=null;
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
                    if(selectedSlot != null)selectedSlot.getModel().setSelected(false);
                    selectedSlot = sv;
                }
                someIsSelected=true;
                break;
            }
        }
        if(!someIsSelected){
            for(SlotView sv: slideView.getSlotViewList()){
                sv.getModel().setSelected(false);
            }
            selectedSlot = null;
        }
    }

    @Override
    public void moveSlot(SlideView slideView, int x, int y) {

    }

    public SlotView getSelectedSlot() {
        return selectedSlot;
    }
}
