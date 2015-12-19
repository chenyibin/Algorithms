package chenyibin.courses;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ObjectUtils;

/**
 * @author Yibin Chen
 */
public class BasicBinarySearchTree<T extends Comparable<T>>
{
    BasicTreeNode<T> root;
    int size;
    
    public BasicBinarySearchTree()
    {
        this.root = null;
        this.size = 0;
    }
    
    public BasicTreeNode<T> insert(T newValue)
    {
        if (this.root == null) {
            this.root = createNode(newValue);
            ++this.size;
            return this.root;
        }
        
        // Our data structure doesn't hold nulls
        if (newValue == null) {
            return null;
        }
        
        BasicTreeNode<T> current = this.root;
        
        boolean done = false;
        while (!done) {
            int comparison = ObjectUtils.compare(current.getValue(), newValue);
            if (comparison == 0) {
                done = true;
            } else if (comparison > 0) {
                // new value is smaller than current value
                if (current.getLeftChild() == null) {
                    ++this.size;
                    BasicTreeNode<T> newNode = createNode(newValue);
                    current.setLeftChild(newNode);
                    updateHeight(current);
                    current = newNode;
                    done = true;
                } else {
                    current = current.getLeftChild();
                }
            } else {
                // new value is greater than current value
                if (current.getRightChild() == null) {
                    ++this.size;
                    BasicTreeNode<T> newNode = createNode(newValue);
                    current.setRightChild(newNode);
                    updateHeight(current);
                    current = newNode;
                    done = true;
                } else {
                    current = current.getRightChild();
                }
            }
        }
        
        return current;
    }
    
    protected BasicTreeNode<T> createNode(T value)
    {
        return new BasicTreeNode<T>(value);
    }
    
    public boolean remove(T removeValue)
    {
        if (this.root == null) {
            return false;
        }
        
        BasicTreeNode<T> removeNode = findNode(removeValue);
        if (removeNode == null) {
            return false;
        }
       
        removeNode(removeNode);
        return true;
    }
    
    public boolean containsValue(T findValue)
    {
        BasicTreeNode<T> foundNode = findNode(findValue);
        return (foundNode != null);
    }
    
    private BasicTreeNode<T> findNode(T findValue)
    {
        BasicTreeNode<T> current = this.root;
        boolean done = false;
        while (!done) {
            int comparison = ObjectUtils.compare(current.getValue(), findValue);
            if (comparison == 0) {
                done = true;
            } else if (comparison > 0) {
                if (current.getLeftChild() == null) {
                    return null;
                }
                current = current.getLeftChild();
            } else {
                if (current.getRightChild() == null) {
                    return null;
                }
                current = current.getRightChild();
            }
        }
        return current;
    }
    
    protected BasicTreeNode<T> removeNode(BasicTreeNode<T> node)
    {
        if (node.getLeftChild() != null && node.getRightChild() != null)
        {
            BasicTreeNode<T> rightMin = getMinValue(node.getRightChild());
            node.setValue(rightMin.getValue());
            return removeNode(rightMin);
        }
        
        --this.size;
        
        // One child or no child
        BasicTreeNode<T> child = (node.getLeftChild() == null)
            ? node.getRightChild()
            : node.getLeftChild();
        
        BasicTreeNode<T> parent = node.getParentNode();
        if (parent == null) {
            this.root = child;
            if (child != null) {
                child.setParentNode(null);
            }
        } else if (parent.getRightChild() == node) {
            parent.setRightChild(child);
        } else {
            parent.setLeftChild(child);
        }
        updateHeight(parent);
        return parent;
    }
    
    public T getMinValue()
    {
        if (this.root == null) {
            return null;
        }

        return getMinValue(this.root).getValue();
    }
    
    private BasicTreeNode<T> getMinValue(BasicTreeNode<T> node)
    {
        while (node.getLeftChild() != null) {
            node = node.getLeftChild();
        }
        return node;
    }
    
     
    public T getMaxValue()
    {
        if (this.root == null) {
            return null;
        }

        return getMaxValue(this.root).getValue();
    }
    
    private BasicTreeNode<T> getMaxValue(BasicTreeNode<T> node)
    {
        while (node.getRightChild() != null) {
            node = node.getRightChild();
        }
        return node;
    }
    
    public List<T> getInOrderTraversal()
    {
        return getInOrderNodeTraversal().stream()
            .map(BasicTreeNode::getValue)
            .collect(Collectors.toList());
    }
    
    List<BasicTreeNode<T>> getInOrderNodeTraversal()
    {
        List<BasicTreeNode<T>> traversal = new ArrayList<>();
        BasicTreeNode<T> current = this.root;
        Deque<BasicTreeNode<T>> traversalStack = new LinkedList<>();
        
        while (current != null || !traversalStack.isEmpty())
        {
            if (current == null) {
                current = traversalStack.pop();
                traversal.add(current);
                current = current.getRightChild();
            } else {
                traversalStack.push(current);
                current = current.getLeftChild();
            }
        }
        
        return traversal;
    }
    
    protected void updateHeight(BasicTreeNode<T> node)
    {
        while (node != null) {
            int oldHeight = node.getHeight();
            BasicTreeNode<T> leftChild = node.getLeftChild();
            BasicTreeNode<T> rightChild = node.getRightChild();
            int newHeight = oldHeight;
            if (leftChild != null && rightChild != null) {
                newHeight = 1 + Math.max(leftChild.getHeight(), rightChild.getHeight());
            } else if (leftChild != null) {
                newHeight = leftChild.getHeight() + 1;
            } else if (rightChild != null) {
                newHeight = rightChild.getHeight() + 1;
            } else {
                newHeight = 0;
            }
            if (newHeight == oldHeight) {
                return;
            }
            node.setHeight(newHeight);
            node = node.getParentNode();
        }
    }
}
