package rudok.commands;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNode;
import rudok.model.workspace.Presentation;
import rudok.model.workspace.Project;
import rudok.model.workspace.RuNodeType;
import rudok.view.MainFrame;
import javax.swing.tree.TreePath;
import java.util.ArrayList;

public class ShareCommand extends AbstractCommand{

    private MyTreeNode parent;
    private MyTreeNode node;
    private String oldName;

    public ShareCommand(MyTreeNode parent,MyTreeNode node, String oldname){
        this.parent = parent;
        this.node = node;
        this.oldName=oldname;
    }


    @Override
    public void doCommand() {
        node.getNode().setIme(node.getNode().getIme() + " - Shared");
        Presentation presentation = (Presentation) node.getNode();
        Project project = (Project) parent.getNode();
        for(RuNode slide:((Presentation) node.getNode()).getChildern()){
            node.add(new MyTreeNode(slide, RuNodeType.SLIDE));
        }
        parent.add(node);
        MainFrame.getInstance().getMyTree().expandPath(new TreePath(node.getPath()));
        project.addSharedPresentation(presentation);
        presentation.getSharedTo().add(project);
    }

    @Override
    public void undoCommand() {
        node.removeAllChildren();
        parent.remove(node);
        node.getNode().setIme(oldName);
        Project project = (Project) parent.getNode();
        Presentation presentation = (Presentation) node.getNode();
        project.removeSharedPresentation(presentation);
        presentation.getSharedTo().remove(project);
    }
}
