package rudok.app;

import rudok.app.gui.swing.view.MainFrame;

public class AppCore {
    public static void main(String[] args) {
        MainFrame b = MainFrame.getInstance();
        b.setLocationRelativeTo(null);
        b.setVisible(true);
    }
}
