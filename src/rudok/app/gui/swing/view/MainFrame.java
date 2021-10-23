package rudok.app.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    private JMenuBar menu;
    private JToolBar toolBar;

    private MainFrame(){

    }
    private void initialise(){}

    private void initialiseGUI(){
        Toolkit kit=Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/2,screenHeight/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("RuDok app");

        menu=new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new Toolbar();
        add(toolBar, BorderLayout.NORTH);


    }


    public static MainFrame getInstance()
    {
        if (instance==null){
            instance = new MainFrame();
            instance.initialiseGUI();
        }
        return instance;
    }
}
