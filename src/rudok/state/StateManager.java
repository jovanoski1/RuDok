package rudok.state;

public class StateManager {
    private State currenState;
    private EditState editState;
    private SlideShowState slideShowState;

    public StateManager(){
        initStates();
    }
    private void initStates(){
        editState = new EditState();
        slideShowState = new SlideShowState();
        currenState = editState;
    }

    public State getCurrenState() {
        return currenState;
    }

    public void setEditState() {
        currenState = editState;
    }

    public void setSlideShowState() {
        currenState = slideShowState;
    }
}
