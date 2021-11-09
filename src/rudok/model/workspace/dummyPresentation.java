package rudok.model.workspace;

public class dummyPresentation {

    Presentation presentation;
    String status;

    public dummyPresentation(Presentation presentation, String status) {
        this.presentation = presentation;
        this.status = status;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
