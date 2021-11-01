package rudok.gui.model.workspace;

import rudok.gui.model.tree.RuNode;
import rudok.gui.model.tree.RuNodeComposite;

public class Project extends RuNodeComposite {

    public Project(String ime,RuNode parent)
    {
        setIme(ime);
        if(parent instanceof Workspace)setParent(parent);
    }

    @Override
    public void addChild(RuNode child) {
        if(!(child instanceof Presentation)) return;
        super.addChild(child);
    }

    @Override
    public void removeChild(RuNode child) {
        if(!(child instanceof Presentation)) return;
        super.removeChild(child);
    }
}
