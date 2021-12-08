package rudok.stateSlot;

import rudok.state.State;

public class SlotStateManager {
    private SlotState currentState;
    private AddSlotState addSlotState;
    private DeleteSlotState deleteSlotState;

    public SlotStateManager(){initStates();}

    private void initStates(){
        addSlotState = new AddSlotState();
        deleteSlotState = new DeleteSlotState();
        currentState = addSlotState;
    }

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
