package rudok.gui.actions;

public class ActionManager {
    private NewAction newAction;
    private InfoAction infoAction;
    private EditAutorAction editAutorAction;
    private EditImageAction editImageAction;

    public ActionManager(){initialiseActions();}

    private void initialiseActions(){
        newAction = new NewAction();
        infoAction = new InfoAction();
        editAutorAction = new EditAutorAction();
        editImageAction = new EditImageAction();
    }

    public EditImageAction getEditImageAction() {
        return editImageAction;
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
}
