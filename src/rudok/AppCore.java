package rudok;

import rudok.gui.view.MainFrame;

public class AppCore {
    public static void main(String[] args) {
        MainFrame b = MainFrame.getInstance();
        b.setLocationRelativeTo(null);
        b.setVisible(true);
    }
}
