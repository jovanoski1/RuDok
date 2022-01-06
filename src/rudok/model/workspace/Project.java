package rudok.model.workspace;

import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;

public class Project extends RuNodeComposite{
    public Project(){}

    public Project(String ime,RuNode parent)
    {
        setIme(ime);
        if(parent instanceof Workspace)setParent(parent);
    }

    @Override
    public void addChild(RuNode child) {
        if(!(child instanceof Presentation) || getChildern().contains(child)) return;
        getChildern().add(child);
        notifySubscribers(new dummyTreeNotification(child,"added"));
    }
    public void addSharedPresentation(Presentation presentation){
        getChildern().add(presentation);
        notifySubscribers(null);
    }
    public void removeSharedPresentation(Presentation presentation){
        getChildern().remove(presentation);
        notifySubscribers(null);
    }

    public boolean containsName(String s){
        for(RuNode p:getChildern()){
            if(p.getIme().equals(s))return true;
        }
        return false;
    }

    @Override
    public void removeChild(RuNode child) {
        if(!(child instanceof Presentation)) return;
        getChildern().remove(child);
        notifySubscribers(new dummyTreeNotification(child,"deleted"));
    }
    public int findChildIndex(Presentation p)
    {
        return getChildern().indexOf(p);
    }

}
