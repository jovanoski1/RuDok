package rudok.gui.view;

import javax.swing.*;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());
        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(MainFrame.getInstance().getActionManager().getEditAutorAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getEditImageAction());
        this.add(fileMenu);
        this.add(editMenu);
        this.add(helpMenu);

    }
}
