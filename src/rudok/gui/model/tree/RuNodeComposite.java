package rudok.gui.model.tree;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode{

    private List<RuNode> childern;

    public RuNodeComposite()
    {

    }

    public void addChild(RuNode child)
    {
        if(child==null)return;
        if(childern==null)childern=new ArrayList<>();
        if(childern.contains(child))return;
        childern.add(child);
    }

    public void removeChild(RuNode child)
    {
        if(child==null)return;
        else if(childern==null)return;
        else if(!childern.contains(child))return;
        childern.remove(child);
    }

    public void setChildern(List<RuNode> childern) {
        this.childern = childern;
    }

    public List<RuNode> getChildern() {
        return childern;
    }
}
