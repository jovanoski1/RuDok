package rudok.factory;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;
import rudok.model.workspace.RuNodeType;

public abstract class AbstractNodeFactory {

    public RuNode getNewRuNode(MyTreeNode myTreeNode){
        return createNode(myTreeNode);
    }
    public static AbstractNodeFactory getFactory(RuNodeType type){
        if(type.equals(RuNodeType.WORKSPACE))return new ProjectFactory();
        else if(type.equals(RuNodeType.PROJECT))return new PresentationFactory();
        else if (type.equals(RuNodeType.PRESENTATION))return new SlideFactory();
        return null;
    }

    public abstract RuNode createNode(MyTreeNode myTreeNode);
}
