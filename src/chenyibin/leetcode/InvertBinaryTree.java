package chenyibin.leetcode;

import java.util.Stack;

import chenyibin.leetcode.common.TreeNode;

public class InvertBinaryTree {

    public TreeNode invertTree(TreeNode root) {
        
        TreeNode current = root;
        
        Stack<TreeNode> traversalStack = new Stack<>();
        while (current != null || !traversalStack.isEmpty()) 
        {
            if (current == null) {
                current = traversalStack.pop();
                
                TreeNode right = current.right;
                current.right = current.left;
                current.left = right;
                current = right;
            } else {
                traversalStack.push(current);
                current = current.left;
            }
        }
        
        return root;
    }
}
