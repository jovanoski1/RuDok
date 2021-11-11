package rudok.model.tree;

import rudok.observer.IPublisher;
import rudok.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNode implements IPublisher {
    private RuNode parent;
    private String ime;
    private List<ISubscriber> subscribers = new ArrayList<>();

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
        this.notifySubscribers(ime);
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

    public RuNode getParent() {
        return parent;
    }

    public void setParent(RuNode parent) {
        this.parent = parent;
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
