package rudok.view;

import rudok.actions.ActionManager;
import rudok.gui.tree.model.MyTreeNode;
import rudok.gui.tree.model.WorkspaceTreeModel;
import rudok.gui.tree.view.MyTree;
import rudok.model.workspace.Workspace;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private MyTree myTree;


    private MainFrame(){

    }
    private void initialise(){
        actionManager = new ActionManager();
        initialiseWorkspaceTree();
        initialiseGUI();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

        JScrollPane scroll=new JScrollPane(myTree);
        scroll.setMinimumSize(new Dimension(200,150));
        JPanel panel=new JPanel();
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,panel);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
        add(split,BorderLayout.CENTER);

    }

    private void initialiseWorkspaceTree()
    {
        myTree=new MyTree();
        WorkspaceTreeModel workspaceModel = new WorkspaceTreeModel(new MyTreeNode(new Workspace("Workspace")));
        myTree.setModel(workspaceModel);
    }

    public static MainFrame getInstance()
    {
        if (instance==null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public MyTree getMyTree() {
        return myTree;
    }
}
