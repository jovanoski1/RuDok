package rudok.model.workspace;

import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;

public class Project extends RuNodeComposite {

    public Project(String ime,RuNode parent)
    {
        setIme(ime);
        if(parent instanceof Workspace)setParent(parent);
    }

    @Override
    public void addChild(RuNode child) {
        if(!(child instanceof Presentation)) return;
        getChildern().add(child);
    }

    @Override
    public void removeChild(RuNode child) {
        if(!(child instanceof Presentation)) return;
        getChildern().remove(child);
    }
}
