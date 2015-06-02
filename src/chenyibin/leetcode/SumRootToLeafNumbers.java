package chenyibin.leetcode;

import java.util.Deque;
import java.util.LinkedList;

import chenyibin.leetcode.common.TreeNode;

public class SumRootToLeafNumbers
{
    public int sumNumbers(TreeNode root) {
        int result = 0;
        
        Deque<TreeNode> traversalStack = new LinkedList<TreeNode>();
        Deque<TreeNode> postOrderStack = new LinkedList<TreeNode>();

        traversalStack.push(root);
        while (!traversalStack.isEmpty())
        {
            TreeNode current = traversalStack.peek();
            TreeNode post = postOrderStack.peek();
            if (current == post) {
                // Second time we are seeing this node
                if (current.right == null && current.left == null)
                {
                    // TODO: Leaf Node

                }
                
                traversalStack.pop();
                postOrderStack.pop();

            } else {
                // First time we are seeing this node
                postOrderStack.push(current);
            }
            
            if (current.right != null) {
                traversalStack.push(current.right);
            }
            if (current.left != null) {
                traversalStack.push(current.left);
            }
        }
        return result;
    }
}
