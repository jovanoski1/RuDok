package rudok.state;

import rudok.view.MainFrame;

public class SlideShowState implements State{
    @Override
    public void changeMode() {
        MainFrame.getInstance().setContentPane(MainFrame.getInstance().getSlideShowPanel());
        MainFrame.getInstance().setJMenuBar(null);
        MainFrame.getInstance().invalidate();
        MainFrame.getInstance().validate();
        System.out.println("SLIDESHOW MODE");
    }
}
