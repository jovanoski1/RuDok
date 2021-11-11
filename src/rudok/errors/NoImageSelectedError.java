package rudok.errors;

import javax.swing.*;
import java.awt.*;

public class NoImageSelectedError implements IError{

    Component parent;
    public NoImageSelectedError(Component c){
        parent=c;
    }
    @Override
    public void showError() {
        JOptionPane.showMessageDialog(parent,"Please select image!","Error",JOptionPane.ERROR_MESSAGE);
    }
}
