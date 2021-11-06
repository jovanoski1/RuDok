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
        this.notifySubscribers(new Object());
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
