package rudok.state;

public class StateManager {
    private State currentState;
    private EditState editState;
    private SlideShowState slideShowState;

    public StateManager(){
        initStates();
    }
    private void initStates(){
        editState = new EditState();
        slideShowState = new SlideShowState();
        currentState = editState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setEditState() {
        currentState = editState;
    }

    public void setSlideShowState() {
        currentState = slideShowState;
    }
}
