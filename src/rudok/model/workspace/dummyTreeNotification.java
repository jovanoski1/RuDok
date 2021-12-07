package rudok.model.workspace;

import rudok.model.tree.RuNode;

public class dummyTreeNotification {

    RuNode treeNode;
    String status;

    public dummyTreeNotification(RuNode treeNode, String status) {
        this.treeNode = treeNode;
        this.status = status;
    }

    public RuNode getTreeNode() {
        return treeNode;
    }

    public void setPresentation(RuNode presentation) {
        this.treeNode = presentation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
