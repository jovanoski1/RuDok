package rudok.model.tree;

import java.util.List;

public abstract class RuNodeComposite extends RuNode{

    private List<RuNode> childern;

    public RuNodeComposite()
    {

    }

    public abstract void addChild(RuNode child);
    public abstract void removeChild(RuNode child);

    public void setChildern(List<RuNode> childern) {
        this.childern = childern;
    }

    public List<RuNode> getChildern() {
        return childern;
    }
}
