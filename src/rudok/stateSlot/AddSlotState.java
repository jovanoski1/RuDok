package rudok.stateSlot;

import rudok.gui.tree.view.SlideView;
import rudok.model.workspace.Slide;
import rudok.model.workspace.Slot;
import rudok.view.MainFrame;

import java.awt.*;

public class AddSlotState implements SlotState{

    @Override
    public void slotAction(SlideView slideView, int x, int y){
        int width = (int) MainFrame.getInstance().getProjectView().getPresentetion().getjSpinner().getValue();
        int ind = MainFrame.getInstance().getProjectView().getPresentetion().getjComboBox().getSelectedIndex();
        float line;
        if(ind == 0)
            line = 0.1f;
        else{
            line=10.0f;
        }
        Slot slot = new Slot(x,y,1.0/slideView.getWidth(),1.0/slideView.getHeight(), MainFrame.getInstance().getProjectView().getPresentetion().getSlotColor());
        slot.setStroke(new BasicStroke(width,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_MITER,10, new float[]{(float) line},0.0f));
        slideView.getModel().addSlot(slot);
        System.out.println("ADD SLOT MODE");
    }

    @Override
    public void moveSlot(SlideView slideView,int x, int y) {

    }
}
