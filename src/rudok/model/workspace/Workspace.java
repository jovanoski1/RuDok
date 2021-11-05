package rudok.model.workspace;

import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;
import rudok.observer.ISubscriber;

import java.util.ArrayList;

public class Workspace extends RuNodeComposite {

    public Workspace(String ime)
    {
        setIme(ime);
    }

    @Override
    public void addChild(RuNode child) {
        if(!(child instanceof Project)) return;
        getChildern().add(child);
    }

    @Override
    public void removeChild(RuNode child) {
        if(!(child instanceof Project)) return;
        getChildern().remove(child);
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
