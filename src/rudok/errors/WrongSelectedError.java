package rudok.errors;

import javax.swing.*;
import java.awt.*;

public class WrongSelectedError implements IError{

    Component parent;
    public WrongSelectedError(Component c){
        parent=c;
    }
    @Override
    public void showError() {
        JOptionPane.showMessageDialog(parent,"Please select Presentation!","Error",JOptionPane.WARNING_MESSAGE);
    }
}
