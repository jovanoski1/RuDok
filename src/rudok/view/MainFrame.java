package rudok.view;

import rudok.actions.ActionManager;
import rudok.errors.IError;
import rudok.gui.tree.model.MyTreeNode;
import rudok.gui.tree.model.WorkspaceTreeModel;
import rudok.gui.tree.view.MyTree;
import rudok.gui.tree.view.ProjectView;
import rudok.model.workspace.Workspace;
import rudok.observer.ISubscriber;
import rudok.state.StateManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance;
    private ActionManager actionManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private MyTree myTree;
    private ProjectView projectView = new ProjectView();
    private JSplitPane split;


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

    public ProjectView getProjectView() {
        return projectView;
    }

    public void setProjectView(ProjectView projectView) {
        this.projectView = projectView;
        split.setRightComponent(projectView);
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
        setLayout(new BorderLayout());
        add(toolBar, BorderLayout.NORTH);

        JScrollPane scroll=new JScrollPane(myTree);
        scroll.setMinimumSize(new Dimension(200,150));
        split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,this.getProjectView());
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
        add(split,BorderLayout.CENTER);
    }

    public JSplitPane getSplit() {
        return split;
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

    @Override
    public void update(Object notification) {
        ((IError)notification).showError();
    }

}
