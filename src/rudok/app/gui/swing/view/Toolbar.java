package rudok.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JToolBar {

    public Toolbar(){
        JButton btnOpen = new JButton();
        btnOpen.setText("Open");
       // btnOpen.setIcon(new ImageIcon("images/open.jpg"));
        //btnOpen.setFocusable(false);

        add(btnOpen);
        setFloatable(false);
        setBackground(new Color(255,255,204));
    }

}
