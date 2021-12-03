package rudok.view;

import javax.swing.*;

public class SlideShowToolbar extends JToolBar {

    public SlideShowToolbar(){

        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getEditModeAction());

    }
}
