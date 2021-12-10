package rudok.stateSlot;

import rudok.state.State;

public class SlotStateManager {
    private SlotState currentState;
    private AddSlotState addSlotState;
    private DeleteSlotState deleteSlotState;
    private MoveSlotState moveSlotState;

    public SlotStateManager(){initStates();}

    private void initStates(){
        addSlotState = new AddSlotState();
        deleteSlotState = new DeleteSlotState();
        moveSlotState = new MoveSlotState();
        currentState = addSlotState;
    }
    public void setMoveSlotState(){ currentState = moveSlotState; }

    public SlotState getCurrentState() {
        return currentState;
    }

    public void setAddSlotState() {
        currentState = addSlotState;
    }

    public void setDeleteSlotState() {
        currentState = deleteSlotState;
    }
}
