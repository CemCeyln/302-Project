import javax.swing.tree.DefaultMutableTreeNode;

public class Node {
    private int nodeID;
    private DefaultMutableTreeNode node;

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    public DefaultMutableTreeNode getNode() {
        return node;
    }

    public void setNode(DefaultMutableTreeNode node) {
        this.node = node;
    }
}
