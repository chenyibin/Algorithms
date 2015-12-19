package chenyibin.courses;

/**
 * @author Yibin Chen
 */
public class AVLTree<T extends Comparable<T>> extends BasicBinarySearchTree<T>
{
    public BasicTreeNode<T> insert(T newValue)
    {
        BasicTreeNode<T> newNode = super.insert(newValue);
        
        BasicTreeNode<T> currentNode = newNode;
        while (currentNode != null) {
            currentNode = rebalanceTreePostInsertion(currentNode, newValue);
            currentNode = currentNode.getParentNode();
        }
        return newNode;
    }
    
    static <N extends Comparable<N>> int getBalance(BasicTreeNode<N> node)
    {
        int leftHeight = (node.getLeftChild() == null)
            ? -1 : node.getLeftChild().getHeight();
        int rightHeight = (node.getRightChild() == null)
            ? -1 : node.getRightChild().getHeight();
        
        return leftHeight - rightHeight;
    }
    
    private BasicTreeNode<T> rebalanceTreePostInsertion(
        BasicTreeNode<T> node,
        T newValue
    ) {
         int balance = getBalance(node);
         
         // case where left is higher than right by at least 2
         if (balance > 1) {
             // node was inserted to the right of the left child
             if (newValue.compareTo(node.getLeftChild().getValue()) > 0) {
                 leftRotation(node.getLeftChild());
             }
             return rightRotation(node);
         }
         // case where right is higher than left by at least 2
         if (balance < -1) {
             // node was inserted to the left of the right child
             if (newValue.compareTo(node.getRightChild().getValue()) < 0) {
                 rightRotation(node.getRightChild());
             }
             return leftRotation(node);
         }
         return node;
         
    }
    
    private BasicTreeNode<T> leftRotation(BasicTreeNode<T> node)
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
        updateHeight(node);
        return right;
    }
    
    private BasicTreeNode<T> rightRotation(BasicTreeNode<T> node)
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
        updateHeight(node);
        return left;
    }
}
