package chenyibin.leetcode.easy;

import chenyibin.leetcode.common.TreeNode;

/**
 * Problem #100 on leetcode.com:
 * Detect whether two trees are the same.
 * @author Yibin Chen
 */
public class SameTree
{
    public boolean isSameTree(TreeNode p, TreeNode q)
    {
        if (p == null || q == null) {
            return p == q;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
