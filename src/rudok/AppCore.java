package rudok;

import rudok.errors.ErrorFactory;
import rudok.view.MainFrame;

public class AppCore {
    public static void main(String[] args) {
        MainFrame b = MainFrame.getInstance();
        b.setLocationRelativeTo(null);
        b.setVisible(true);
        ErrorFactory.getInsance().addSubscriber(b);
    }
}
