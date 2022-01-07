package rudok.model.workspace;

import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;

import java.util.ArrayList;
import java.util.List;

public class Presentation extends RuNodeComposite {

    private String autor;
    private String slika;
    private List<Project> sharedTo = new ArrayList<>();

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
        notifySubscribers(null);
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
        notifySubscribers(null);
    }

    @Override
    public void addChild(RuNode child) {
        if(!(child instanceof Slide) || getChildern().contains(child)) return;
        getChildern().add(child);
        notifySubscribers(new dummyTreeNotification(child,"added"));
    }

    @Override
    public void removeChild(RuNode child) {
        if(!(child instanceof Slide)) return;
        getChildern().remove(child);
        notifySubscribers(new dummyTreeNotification(child,"deleted"));
    }

    @Override
    public String toString() {
        return getIme();
    }

    public List<Project> getSharedTo() {
        return sharedTo;
    }

    public void setSharedTo(List<Project> sharedTo) {
        this.sharedTo = sharedTo;
    }

    @Override
    public void notifyProjectChange() {
        ((Project)getParent()).notifyProjectChange();
        for(Project p : sharedTo){
            p.notifyProjectChange();
        }
    }
}
