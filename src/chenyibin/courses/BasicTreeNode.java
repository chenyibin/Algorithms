package chenyibin.courses;

public class BasicTreeNode<T extends Comparable<T>> {

    private T value;
    private BasicTreeNode<T> leftChild;
    private BasicTreeNode<T> rightChild;
    private BasicTreeNode<T> parentNode;
    private int height = 0;
    
    public BasicTreeNode(T value) {
        this.value = value;
        this.parentNode = null;
        this.rightChild = null;
        this.leftChild = null;
    }

    public BasicTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BasicTreeNode<T> leftChild) {
        this.leftChild = leftChild;
        if (leftChild != null) {
            leftChild.setParentNode(this);
        }
    }

    public BasicTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BasicTreeNode<T> rightChild) {
        this.rightChild = rightChild;
        if (rightChild != null) {
            rightChild.setParentNode(this);
        }
    }

    public BasicTreeNode<T> getParentNode() {
        return parentNode;
    }

    public void setParentNode(BasicTreeNode<T> parentNode) {
        this.parentNode = parentNode;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
    public String toString() {
        return this.value.toString();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
