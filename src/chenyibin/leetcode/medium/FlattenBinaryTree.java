package chenyibin.leetcode.medium;

import chenyibin.leetcode.common.TreeNode;

public class FlattenBinaryTree
{
    TreeNode prev = null;
    
    public void flatten(TreeNode root)
    {
        if (root == null) {
            return;
        }
        flatten(root.left);
        flatten(root.right);
        
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
