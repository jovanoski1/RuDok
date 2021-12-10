package rudok.stateSlot;

import rudok.gui.tree.view.SlideView;
import rudok.gui.tree.view.SlotView;
import rudok.model.workspace.Slot;

public class MoveSlotState implements SlotState{
    Slot selectedSlot = null;
    int dx = -1;
    int dy = -1;
    @Override
    public void slotAction(SlideView slideView, int x, int y) {
        selectedSlot = null;
        for(SlotView sv: slideView.getSlotViewList()){
            if(sv.elementAt(x,y)){
                selectedSlot = sv.getModel();
                dx = Math.abs(selectedSlot.getX()-x);
                dy = Math.abs(selectedSlot.getY()-y);
                break;
            }
        }
        System.out.println("MOVE SLOT PRESSED MODE");
    }

    @Override
    public void moveSlot(SlideView slideView,int x, int y) {
        if(selectedSlot!=null){
            int widthScaled = (int) (selectedSlot.getWidth()*selectedSlot.getxScale()*slideView.getWidth());
            int heightScaled = (int) (selectedSlot.getHeight()*selectedSlot.getyScale()*slideView.getHeight());
            if(x-dx>=0&&x-dx+widthScaled<=slideView.getWidth()&&y-dy>=0&&y-dy+heightScaled<=slideView.getHeight())
                selectedSlot.setPosition(x-dx, y-dy);
        }
        else System.out.println("NULL");
    }
}
