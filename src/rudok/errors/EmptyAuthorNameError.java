package rudok.errors;

import rudok.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class EmptyAuthorNameError implements IError{

    Component parent;
    public EmptyAuthorNameError(Component c){
        parent=c;
    }

    @Override
    public void showError() {
        JOptionPane.showMessageDialog(parent,"Autor name cannot be empty!","Error",JOptionPane.WARNING_MESSAGE);
    }
}
