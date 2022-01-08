package rudok.actions;

public class ActionManager {
    private NewAction newAction;
    private InfoAction infoAction;
    private EditAutorAction editAutorAction;
    private EditImageAction editImageAction;
    private DeleteAction deleteAction;
    private EditModeAction editModeAction;
    private SlideShowModeAction slideShowModeAction;
    private AddSlotModeAction addSlotModeAction;
    private DeleteSlotModeAction deleteSlotModeAction;
    private MoveSlotAction moveSlotAction;
    private SelectSlotAction selectSlotAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private SharePresentationAction sharePresentationAction;
    private SaveProjectAction saveProjectAction;
    private OpenProjectAction openProjectAction;
    private SaveWorkspaceAction saveWorkspaceAction;
    private SavePresentationAction savePresentationAction;
    private OpenPresentationAction openPresentationAction;

    public ActionManager(){initialiseActions();}

    private void initialiseActions() {
        newAction = new NewAction();
        infoAction = new InfoAction();
        editAutorAction = new EditAutorAction();
        editImageAction = new EditImageAction();
        deleteAction = new DeleteAction();
        slideShowModeAction = new SlideShowModeAction();
        editModeAction = new EditModeAction();
        addSlotModeAction = new AddSlotModeAction();
        deleteSlotModeAction = new DeleteSlotModeAction();
        moveSlotAction = new MoveSlotAction();
        selectSlotAction = new SelectSlotAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        sharePresentationAction = new SharePresentationAction();
        saveProjectAction = new SaveProjectAction();
        openProjectAction = new OpenProjectAction();
        saveWorkspaceAction = new SaveWorkspaceAction();
        savePresentationAction = new SavePresentationAction();
        openPresentationAction = new OpenPresentationAction();
    }

    public OpenPresentationAction getOpenPresentationAction() {
        return openPresentationAction;
    }

    public void setOpenPresentationAction(OpenPresentationAction openPresentationAction) {
        this.openPresentationAction = openPresentationAction;
    }

    public SavePresentationAction getSavePresentationAction() {
        return savePresentationAction;
    }

    public void setSavePresentationAction(SavePresentationAction savePresentationAction) {
        this.savePresentationAction = savePresentationAction;
    }

    public SaveWorkspaceAction getSaveWorkspaceAction() {
        return saveWorkspaceAction;
    }

    public void setSaveWorkspaceAction(SaveWorkspaceAction saveWorkspaceAction) {
        this.saveWorkspaceAction = saveWorkspaceAction;
    }

    public OpenProjectAction getOpenProjectAction() {
        return openProjectAction;
    }

    public void setOpenProjectAction(OpenProjectAction openProjectAction) {
        this.openProjectAction = openProjectAction;
    }

    public SaveProjectAction getSaveProjectAction() {
        return saveProjectAction;
    }

    public void setSaveProjectAction(SaveProjectAction saveProjectAction) {
        this.saveProjectAction = saveProjectAction;
    }

    public SharePresentationAction getSharePresentationAction() {
        return sharePresentationAction;
    }

    public void setSharePresentationAction(SharePresentationAction sharePresentationAction) {
        this.sharePresentationAction = sharePresentationAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public void setRedoAction(RedoAction redoAction) {
        this.redoAction = redoAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public void setUndoAction(UndoAction undoAction) {
        this.undoAction = undoAction;
    }

    public SelectSlotAction getSelectSlotAction() {
        return selectSlotAction;
    }

    public void setSelectSlotAction(SelectSlotAction selectSlotAction) {
        this.selectSlotAction = selectSlotAction;
    }

    public MoveSlotAction getMoveSlotAction() {
        return moveSlotAction;
    }

    public void setMoveSlotAction(MoveSlotAction moveSlotAction) {
        this.moveSlotAction = moveSlotAction;
    }

    public AddSlotModeAction getAddSlotModeAction() {
        return addSlotModeAction;
    }

    public void setAddSlotModeAction(AddSlotModeAction addSlotModeAction) {
        this.addSlotModeAction = addSlotModeAction;
    }

    public DeleteSlotModeAction getDeleteSlotModeAction() {
        return deleteSlotModeAction;
    }

    public void setDeleteSlotModeAction(DeleteSlotModeAction deleteSlotModeAction) {
        this.deleteSlotModeAction = deleteSlotModeAction;
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
