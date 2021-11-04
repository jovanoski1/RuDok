package rudok.model.workspace;

import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;

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
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    @Override
    public void addChild(RuNode child) {
        if(!(child instanceof Slide)) return;
        getChildern().add(child);
    }

    @Override
    public void removeChild(RuNode child) {
        if(!(child instanceof Slide)) return;
        getChildern().remove(child);
    }
}
