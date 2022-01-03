package rudok.stateSlot;


public class SlotStateManager {
    private SlotState currentState;
    private AddSlotState addSlotState;
    private DeleteSlotState deleteSlotState;
    private MoveSlotState moveSlotState;
    private SelectSlotState selectSlotState;

    public SlotStateManager(){initStates();}

    private void initStates(){
        addSlotState = new AddSlotState();
        deleteSlotState = new DeleteSlotState();
        moveSlotState = new MoveSlotState();
        selectSlotState = new SelectSlotState();
        currentState = addSlotState;
    }
    public void setSelectSlotState(){ currentState = selectSlotState; }
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

    public AddSlotState getAddSlotState() {
        return addSlotState;
    }

    public SelectSlotState getSelectSlotState() {
        return selectSlotState;
    }
}
