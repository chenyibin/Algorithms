package chenyibin.courses;

public class AVLTree<T extends Comparable<T>> extends BasicBinarySearchTree<T>
{
    public BasicTreeNode<T> insert(T newValue)
    {
        BasicTreeNode<T> avlNode = super.insert(newValue);
        
        return avlNode;
    }
    
    private void rebalanceTree(BasicTreeNode<T> node)
    {
        int leftHeight = (node.getLeftChild() == null)
            ? -1 : node.getLeftChild().getHeight();
        int rightHeight = (node.getRightChild() == null)
            ? -1 : node.getRightChild().getHeight();
    }
    
    private void leftRotation(BasicTreeNode<T> node)
    {
        BasicTreeNode<T> parent = node.getParentNode();
        BasicTreeNode<T> right = node.getRightChild();
        
        if (parent == null) {
            this.root = right;
            right.setParentNode(null);
        } else if (parent.getRightChild() == node) {
            parent.setRightChild(right);
        } else if (parent.getLeftChild() == node) {
            parent.setLeftChild(right);
        }
        
        BasicTreeNode<T> rightLeft = right.getLeftChild();
        node.setRightChild(rightLeft);
        right.setLeftChild(node);
    }
    
    private void rightRotation(BasicTreeNode<T> node)
    {
        BasicTreeNode<T> parent = node.getParentNode();
        BasicTreeNode<T> left = node.getLeftChild();
        
        if (parent == null) {
            this.root = left;
            left.setParentNode(null);
        } else if (parent.getRightChild() == node) {
            parent.setRightChild(left);
        } else if (parent.getLeftChild() == node) {
            parent.setLeftChild(left);
        }
        
        BasicTreeNode<T> leftRight = left.getRightChild();
        node.setLeftChild(leftRight);
        left.setRightChild(node);
    }
}
