package rudok.errors;

public class WorkspaceDeleteAError extends AError {
    @Override
    public String showError() {
        return "Workspace can't be deleted!";//JOptionPane.showMessageDialog(parent,"Workspace can't be deleted!","Error",JOptionPane.ERROR_MESSAGE);
    }
}
