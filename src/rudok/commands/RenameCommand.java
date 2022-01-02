package rudok.commands;

import rudok.gui.tree.model.MyTreeNode;

public class RenameCommand extends AbstractCommand{

    MyTreeNode node;
    String staroIme;
    String novoIme;

    public RenameCommand(MyTreeNode node, String staroIme,String novoIme) {
        this.node = node;
        this.staroIme = staroIme;
        this.novoIme=novoIme;
    }

    @Override
    public void doCommand() {
        node.getNode().setIme(novoIme);
    }

    @Override
    public void undoCommand() {
        node.getNode().setIme(staroIme);
    }
}
