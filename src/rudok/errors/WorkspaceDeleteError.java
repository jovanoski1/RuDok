package rudok.errors;

import javax.swing.*;
import java.awt.*;

public class WorkspaceDeleteError implements IError{

    Component parent;
    public WorkspaceDeleteError(Component c){
        parent=c;
    }
    @Override
    public void showError() {
        JOptionPane.showMessageDialog(parent,"Workspace can't be deleted!","Error",JOptionPane.ERROR_MESSAGE);
    }
}
