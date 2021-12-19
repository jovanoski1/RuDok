package rudok.factory;

import rudok.gui.tree.model.MyTreeNode;
import rudok.model.tree.RuNode;

public abstract class AbstractNodeFactory {

    public RuNode getNewRuNode(MyTreeNode myTreeNode){
        return createNode(myTreeNode);
    }

    public abstract RuNode createNode(MyTreeNode myTreeNode);
}
