package rudok.model.workspace;

import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;
import rudok.observer.ISubscriber;

import java.util.ArrayList;

public class Presentation extends RuNodeComposite {

    private String autor;
    private String slika;

    public Presentation(RuNode parent,String autor,String ime,String slika)
    {
        setIme(ime);
        if(parent instanceof Project)setParent(parent);
        setAutor(autor);
        setSlika(slika);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
        notifySubscribers(new Object());
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
        notifySubscribers(new Object());
    }

    @Override
    public void addChild(RuNode child) {
        if(!(child instanceof Slide)) return;
        getChildern().add(child);
        notifySubscribers(new Object());
    }

    @Override
    public void removeChild(RuNode child) {
        if(!(child instanceof Slide)) return;
        getChildern().remove(child);
        notifySubscribers(new Object());
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
