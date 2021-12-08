package rudok.factory;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNode;
import rudok.model.workspace.Project;
import rudok.model.workspace.RuNodeType;
import rudok.model.workspace.Workspace;
import rudok.view.MainFrame;

import javax.swing.*;

public class ProjectFactory extends AbstractNodeFactory{

    @Override
    public RuNode createNode(MyTreeNode myTreeNode) {
        Project project = new Project("Projekat "+(myTreeNode.getChildCount()+1),myTreeNode.getNode());
        ((Workspace)myTreeNode.getNode()).addChild(project);
        project.addSubscriber(MainFrame.getInstance().getProjectView());
        return project;
    }
}
