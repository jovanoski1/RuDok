package rudok.model.workspace;

import rudok.model.tree.RuNode;
import rudok.observer.ISubscriber;

import java.util.ArrayList;

public class Slide extends RuNode {
    private int redniBroj;

    public Slide(int redniBroj,RuNode parent)
    {
        this.redniBroj=redniBroj;
        setIme("Slide "+redniBroj);
        if(parent instanceof Presentation)setParent(parent);
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }
}
