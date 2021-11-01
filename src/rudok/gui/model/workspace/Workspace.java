package rudok.gui.model.workspace;

import rudok.gui.model.tree.RuNode;
import rudok.gui.model.tree.RuNodeComposite;

public class Workspace extends RuNodeComposite {

    public Workspace(String ime)
    {
        setIme(ime);
    }

    @Override
    public void addChild(RuNode child) {
        if(!(child instanceof Project)) return;
        super.addChild(child);
    }

    @Override
    public void removeChild(RuNode child) {
        if(!(child instanceof Project)) return;
        super.removeChild(child);
    }
}
