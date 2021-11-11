package rudok.model.workspace;

import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;
import rudok.observer.ISubscriber;

import java.util.ArrayList;

public class Project extends RuNodeComposite{

    public Project(String ime,RuNode parent)
    {
        setIme(ime);
        if(parent instanceof Workspace)setParent(parent);
    }

    @Override
    public void addChild(RuNode child) {
        if(!(child instanceof Presentation)) return;
        getChildern().add(child);
        notifySubscribers(new dummyPresentation((Presentation) child,"added"));
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
        notifySubscribers(new dummyPresentation((Presentation) child,"deleted"));
    }
    public int findChildIndex(Presentation p)
    {
        return getChildern().indexOf(p);
    }

}
