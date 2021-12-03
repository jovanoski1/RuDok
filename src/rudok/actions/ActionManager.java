package rudok.actions;

public class ActionManager {
    private NewAction newAction;
    private InfoAction infoAction;
    private EditAutorAction editAutorAction;
    private EditImageAction editImageAction;
    private DeleteAction deleteAction;
    private EditModeAction editModeAction;
    private SlideShowModeAction slideShowModeAction;

    public ActionManager(){initialiseActions();}

    private void initialiseActions(){
        newAction = new NewAction();
        infoAction = new InfoAction();
        editAutorAction = new EditAutorAction();
        editImageAction = new EditImageAction();
        deleteAction = new DeleteAction();
        slideShowModeAction = new SlideShowModeAction();
        editModeAction = new EditModeAction();
    }

    public EditImageAction getEditImageAction() {
        return editImageAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(DeleteAction deleteAction) {
        this.deleteAction = deleteAction;
    }

    public void setEditImageAction(EditImageAction editImageAction) {
        this.editImageAction = editImageAction;
    }

    public EditAutorAction getEditAutorAction() {
        return editAutorAction;
    }

    public void setEditAutorAction(EditAutorAction editAutorAction) {
        this.editAutorAction = editAutorAction;
    }

    public NewAction getNewAction() {
        return newAction;
    }

    public void setNewAction(NewAction newAction) {
        this.newAction = newAction;
    }

    public InfoAction getInfoAction() {
        return infoAction;
    }

    public void setInfoAction(InfoAction infoAction) {
        this.infoAction = infoAction;
    }

    public EditModeAction getEditModeAction() {
        return editModeAction;
    }

    public void setEditModeAction(EditModeAction editModeAction) {
        this.editModeAction = editModeAction;
    }

    public SlideShowModeAction getSlideShowModeAction() {
        return slideShowModeAction;
    }

    public void setSlideShowModeAction(SlideShowModeAction slideShowModeAction) {
        this.slideShowModeAction = slideShowModeAction;
    }
}
