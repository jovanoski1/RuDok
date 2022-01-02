package rudok.model.workspace;

import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;

public class Workspace extends RuNodeComposite {

    public Workspace(String ime)
    {
        setIme(ime);
    }

    @Override
    public void addChild(RuNode child) {
        if(!(child instanceof Project) || getChildern().contains(child)) return;
        getChildern().add(child);
    }

    @Override
    public void removeChild(RuNode child) {
        if(!(child instanceof Project)) return;
        getChildern().remove(child);
    }
}
