package rudok.view;

import rudok.Utils;
import rudok.actions.ActionManager;
import rudok.commands.CommandManager;
import rudok.errors.AError;
import rudok.gui.tree.model.MyTreeNode;
import rudok.gui.tree.model.WorkspaceTreeModel;
import rudok.gui.tree.view.MyTree;
import rudok.gui.tree.view.ProjectView;
import rudok.model.tree.RuNode;
import rudok.model.workspace.Project;
import rudok.model.workspace.RuNodeType;
import rudok.model.workspace.Workspace;
import rudok.observer.ISubscriber;
import rudok.view.popup.WorkspaceChooserDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class MainFrame extends JFrame implements ISubscriber {

    private static MainFrame instance;
    private ActionManager actionManager;
    private CommandManager commandManager;
    private JMenuBar menu;
    private JToolBar toolBar;
    private MyTree myTree;
    private ProjectView projectView = new ProjectView();
    private JSplitPane split;


    private MainFrame(){

    }
    private void initialise(){
        actionManager = new ActionManager();
        commandManager = new CommandManager();
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
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    Writer fileWriter = new FileWriter(Utils.getLastWorkspacePath(), false);
                    MyTreeNode workspaceMTN = (MyTreeNode) myTree.getModel().getRoot();
                    Workspace workspace = (Workspace) workspaceMTN.getNode();
                    for(RuNode ruNode : workspace.getChildern()){
                        Project project = (Project) ruNode;
                        System.out.println(project.getIme() + " "+project.getProjectFile());
                        if(project.getProjectFile()==null || project.isChanged())continue;
                        Utils.saveProject(project,project.getProjectFile());
                        fileWriter.append(project.getProjectFile().getPath());
                        fileWriter.append("\n");
                    }
                    fileWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {
                new WorkspaceChooserDialog();
                super.windowOpened(e);
            }
        });

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
        WorkspaceTreeModel workspaceModel = new WorkspaceTreeModel(new MyTreeNode(new Workspace("Workspace"),RuNodeType.WORKSPACE));
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
        JOptionPane.showMessageDialog(this,((AError)notification).showError(),"Error",JOptionPane.ERROR_MESSAGE);
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
