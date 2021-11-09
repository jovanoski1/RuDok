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

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.getSubscribers() ==null)
            this.setSubscribers(new ArrayList<>());
        if(this.getSubscribers().contains(sub))
            return;
        this.getSubscribers().add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.getSubscribers() == null || !this.getSubscribers().contains(sub))
            return;
        this.getSubscribers().remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || this.getSubscribers() == null || this.getSubscribers().isEmpty())
            return;

        for(ISubscriber listener : getSubscribers()){
            listener.update(notification);
        }
    }
}
