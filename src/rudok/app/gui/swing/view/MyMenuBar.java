package rudok.app.gui.swing.view;

import javax.swing.*;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");

        JMenu helpMenu = new JMenu("Help");

        this.add(fileMenu);
        this.add(helpMenu);

    }
}
