package rudok.factory;

import rudok.model.tree.RuNode;
import rudok.model.workspace.Project;
import rudok.model.workspace.Workspace;
import rudok.view.MainFrame;

public class ProjectFactory extends AbstractNodeFactory{

    @Override
    public RuNode createNode(RuNode ruNode) {
        Project project = new Project("Projekat "+(((Workspace)ruNode).getChildern().size()+1),ruNode);
        ((Workspace)ruNode).addChild(project);
        project.addSubscriber(MainFrame.getInstance().getProjectView());
        return project;
    }
}
