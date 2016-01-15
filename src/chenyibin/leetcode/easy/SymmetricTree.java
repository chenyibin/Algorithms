package chenyibin.leetcode.easy;

import java.util.Stack;

import chenyibin.leetcode.common.TreeNode;

/**
 * Problem #101 on leetcode.com:
 * Given a binary tree, check whether it is a mirror if itself.
 * @author Yibin Chen
 */
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
            
            if (left == null || right == null) {
                if (left != right) {
                    return false;
                }
            } else {
                if (left.val != right.val) {
                    return false;
                }

                leftStack.push(left.left);
                leftStack.push(left.right);
                rightStack.push(right.right);
                rightStack.push(right.left);
            }
        }
        
        return leftStack.size() == rightStack.size();
    }
    
    
}
