package chenyibin.web;

import java.util.Stack;

import chenyibin.leetcode.common.TreeNode;

public class KthLargestElementInBinaryTree
{
    TreeNode root;
    public KthLargestElementInBinaryTree(TreeNode root)
    {
        this.root = root;
    }

    public int findKth(int k)
    {
        Stack<TreeNode> traversalStack = new Stack<TreeNode>();

        TreeNode resultNode = null;

        TreeNode current = this.root;

        while ((!traversalStack.isEmpty() || current != null) && k != 0)
        {
            if (current == null) {
                resultNode = traversalStack.pop();
                current = resultNode.left;
                --k;
            } else {
                traversalStack.add(current);
                current = current.right;
            }
        }

        return resultNode.val;
    }
}
