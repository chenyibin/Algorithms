package chenyibin.leetcode.easy;

import chenyibin.leetcode.common.TreeNode;

/**
 * Problem #110 on leetcode.com:
 * Given a binary tree, determine if it is height-balanced.
 * @author Yibin Chen
 *
 */
public class BalancedBinaryTree
{
    public boolean isBalanced(TreeNode root)
    {
        return heightBalance(root) != -1;
    }
    
    public int heightBalance(TreeNode node)
    {
        if (node == null) {
            return 0;
        }
        int leftHeight = heightBalance(node.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = heightBalance(node.right);
        if (rightHeight == -1) {
            return -1;
        }
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
