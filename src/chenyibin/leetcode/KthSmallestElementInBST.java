package chenyibin.leetcode;

import java.util.Stack;

import chenyibin.leetcode.common.TreeNode;

/**
 * Problem #230 on leetcode.com:</br>
 * Given a binary search tree, write a function to find the kth smallest element in it.
 * 
 * @author Yibin Chen
 */
public class KthSmallestElementInBST
{
    
    public int kthSmallest(TreeNode root, int k) {
        
        Stack<TreeNode> traversalStack = new Stack<>();
        TreeNode current = root;
        
        while (current != null || !traversalStack.isEmpty())
        {
            if (current == null) {
                current = traversalStack.pop();
                --k;
                if (k == 0) {
                    return current.val;
                }
                current = current.right;
            } else {
                traversalStack.push(current);
                current = current.left;
            }
        }
        return 0;
    }
}
