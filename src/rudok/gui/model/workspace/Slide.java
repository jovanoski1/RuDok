package rudok.gui.model.workspace;

import rudok.gui.model.tree.RuNode;

public class Slide extends RuNode {
    private int redniBroj;

    public Slide(int redniBroj,RuNode parent)
    {
        redniBroj=redniBroj;
        if(parent instanceof Presentation)setParent(parent);
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }
}
