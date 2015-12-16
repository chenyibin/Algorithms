package chenyibin.leetcode;

import java.util.Stack;

import chenyibin.leetcode.common.TreeNode;

public class SymmetricTree
{
    public boolean isSymmetric(TreeNode root)
    {
        if (root == null) {
            return true;
        }
        
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        leftStack.add(root.left);
        rightStack.add(root.right);
        
        while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
            TreeNode left = leftStack.pop();
            TreeNode right = rightStack.pop();
            
            if (left.val != right.val) {
                return false;
            }
            leftStack.push(left.left);
            leftStack.push(left.right);
            rightStack.push(right.right);
            rightStack.push(right.left);
        }
        
        return leftStack.size() == 0 && rightStack.size() == 0;
    }
    
    
}
