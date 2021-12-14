package rudok.stateSlot;

import rudok.gui.tree.view.SlideView;
import rudok.gui.tree.view.SlotView;
import rudok.model.workspace.Slot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SelectSlotState implements SlotState{

    @Override
    public void slotAction(SlideView slideView, int x, int y) {
        boolean someIsSelected=false;
        for(SlotView sv: slideView.getSlotViewList()){
            if(sv.elementAt(x,y)){
                if(sv.getModel().isSelected()){
                    sv.getModel().setSelected(false);
                }
                else sv.getModel().setSelected(true);
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
