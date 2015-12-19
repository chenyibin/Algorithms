package chenyibin.leetcode;

import chenyibin.leetcode.common.TreeNode;

/**
 * Problem #236 from leetcode.com:</br>
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * The lowest common ancestor is the lowest node in T that has both p and q as descendants.
 * Note that for this question a node is also a descendant of itself.
 * 
 * @author Yibin Chen
 */
public class LowestCommonAncestoryBinaryTree
{
    TreeNode p;
    TreeNode q;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        this.p = p;
        this.q = q;
        return searcher(root);
    }
    
    public TreeNode searcher(TreeNode node)
    {
        if (node == null || node == q || node == p) {
            return node;
        }
        TreeNode left = searcher(node.left);
        TreeNode right = searcher(node.right);
        if (left != null && right != null) {
            return node;
        }
        if (left != null) {
            return left;
        }
        return right;
    }
    
}
