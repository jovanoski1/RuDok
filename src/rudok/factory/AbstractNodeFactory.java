package rudok.factory;

import rudok.model.tree.RuNode;

public abstract class AbstractNodeFactory {

    public RuNode getNewRuNode(RuNode ruNode){
        return createNode(ruNode);
    }

    public abstract RuNode createNode(RuNode ruNode);
}
