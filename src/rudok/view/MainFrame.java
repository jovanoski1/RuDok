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
    private StateManager stateManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private JToolBar slideshowToolbar;
    private MyTree myTree;
    private ProjectView projectView = new ProjectView();
    private JSplitPane split;
    private JPanel mainPanel = new JPanel();
    private JPanel slideShowPanel = new JPanel();


    private MainFrame(){

    }
    private void initialise(){
        actionManager = new ActionManager();
        stateManager = new StateManager();
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
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(toolBar, BorderLayout.NORTH);

        slideshowToolbar = new SlideShowToolbar();
        slideShowPanel.setLayout(new BorderLayout());
        slideShowPanel.add(slideshowToolbar, BorderLayout.NORTH);

        JScrollPane scroll=new JScrollPane(myTree);
        scroll.setMinimumSize(new Dimension(200,150));
        split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,this.getProjectView());
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
        mainPanel.add(split,BorderLayout.CENTER);
        this.setContentPane(mainPanel);
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

    public void startEditState()
    {
        this.stateManager.setEditState();
        changeMode();
    }
    public void startSlideShowState(){
        this.stateManager.setSlideShowState();
        changeMode();
    }
    public void changeMode(){
        this.stateManager.getCurrentState().changeMode();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getSlideShowPanel() {
        return slideShowPanel;
    }

    public JMenuBar getMenu() {
        return menu;
    }
}
