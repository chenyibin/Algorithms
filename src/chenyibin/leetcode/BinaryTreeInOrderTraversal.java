package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import chenyibin.leetcode.common.TreeNode;

/**
 * Problem #94 from leetcode.com:</br>
 * Given a binary tree, return the inorder traversal of its nodes' values.</br>
 * Solve this problem iteratively.</br>
 * 
 * <strong>Solution:</strong></br>
 * Solution is simply to keep track of which node was previously discovered</br>
 * and only push the right node the second time we see a particular parent.</br>
 * A node can be immediately added to the result list if it doesn't have a left child.</br>
 * 
 * @author Yibin Chen
 */
public class BinaryTreeInOrderTraversal
{
    public List<Integer> inOrderTraversal(TreeNode root)
    {
        List<Integer> ll = new LinkedList<Integer>();
        // Handle null case
        if (root == null)
            return ll;		

        Deque<TreeNode> traversalStack = new LinkedList<TreeNode>();
        Deque<TreeNode> postOrderStack = new LinkedList<TreeNode>();

        traversalStack.push(root);
        while (!traversalStack.isEmpty())
        {
            TreeNode current = traversalStack.peek();

            if (current.left == null) {
                traversalStack.pop();
                ll.add(current.val);
                if (current.right != null) {
                    traversalStack.push(current.right);
                }
            } else if (postOrderStack.peek() == current) {
                postOrderStack.pop();
                traversalStack.pop();
                ll.add(current.val);
                if (current.right != null) {
                    traversalStack.push(current.right);
                }
            } else {
                postOrderStack.push(current);
                traversalStack.push(current.left);
            }
        }
        return ll;
    }

    public List<Integer> inOrderWithOneStack(TreeNode root)
    {
        List<Integer> result = new ArrayList<>();

        TreeNode current = root;
        Deque<TreeNode> traversalStack = new LinkedList<>();
        while (current != null || !traversalStack.isEmpty()) {
            if (current == null) {
                current = traversalStack.pop();
                result.add(current.val);
                current = current.right;
            } else {
                traversalStack.push(current);
                current = current.left;
            }
        }
        return result;
    }
}
