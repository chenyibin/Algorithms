package chenyibin.leetcode.easy;

import chenyibin.leetcode.common.TreeNode;

/**
 * Problem #112 on leetcode.com:
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum. 
 * @author Yibin Chen
 */
public class PathSum
{
    public boolean hasPathSum(TreeNode node, int sum)
    {
        if (node == null) {
            return false;
        }
        if (node.left == null && node.right == null) {
            return (node.val == sum);
        }
        int diff = sum - node.val;
        
        return hasPathSum(node.left, diff) || hasPathSum(node.right, diff);
    }
}
