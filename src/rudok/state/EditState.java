package rudok.state;

import rudok.view.MainFrame;

public class EditState  implements State{
    @Override
    public void changeMode() {
        MainFrame.getInstance().setContentPane(MainFrame.getInstance().getMainPanel());
        MainFrame.getInstance().setJMenuBar(MainFrame.getInstance().getMenu());
        MainFrame.getInstance().invalidate();
        MainFrame.getInstance().validate();
        System.out.println("EDIT MODE");
    }
}
