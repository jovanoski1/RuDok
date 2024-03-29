package rudok.gui.tree.model;

import rudok.model.tree.RuNode;
import rudok.model.tree.RuNodeComposite;
import rudok.model.workspace.RuNodeType;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;

public class MyTreeNode extends DefaultMutableTreeNode {

    private RuNode node;
    private RuNodeType type;

    public MyTreeNode(RuNode node, RuNodeType type){
        this.node=node;
        this.type=type;
    }

    @Override
    public String toString() {
        return node.toString();
    }

    @Override
    public TreeNode getChildAt(int index) {
        if(node instanceof RuNodeComposite)return super.getChildAt(index);
        return null;
    }

    @Override
    public int getChildCount() {
        if(node instanceof RuNodeComposite)return super.getChildCount();
        return 0;
    }

    @Override
    public void remove(MutableTreeNode aChild) {
        super.remove(aChild);
    }

    @Override
    public int getIndex(TreeNode aChild) {
        if(node instanceof RuNodeComposite)return super.getIndex(aChild);
        return 0;
    }

    @Override
    public Enumeration<TreeNode> children() {
        if(node instanceof RuNodeComposite)return super.children();
        return null;
    }

    @Override
    public boolean getAllowsChildren() {
        return node instanceof RuNodeComposite;
    }

    @Override
    public void add(MutableTreeNode newChild) {
        super.add(newChild);
    }

    @Override
    public boolean isLeaf() {
        return !(node instanceof RuNodeComposite);
    }

    public RuNode getNode() {
        return node;
    }

    public void setNode(RuNode node) {
        this.node = node;
    }

    public RuNodeType getType() {
        return type;
    }

    public void setType(RuNodeType type) {
        this.type = type;
    }
}
