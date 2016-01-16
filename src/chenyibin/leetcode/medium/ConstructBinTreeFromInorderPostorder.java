package chenyibin.leetcode.medium;

import java.util.Stack;

import chenyibin.leetcode.common.TreeNode;

public class ConstructBinTreeFromInorderPostorder {

    public TreeNode buildTree(int[] inorder, int[] postorder)
    {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
        stack.push(dummy);
        
        TreeNode parent = null;
        int post = postorder.length - 1;
        int in = post;

        while (in >= 0)
        {
            if (stack.peek().val == inorder[in]) {
                parent = stack.pop();
                --in;
            } else {
                TreeNode newNode = new TreeNode(postorder[post--]);
                if (parent == null) {
                    stack.peek().right = newNode;
                } else {
                    parent.left = newNode;
                    parent = null;
                }
                stack.push(newNode);
            }
        }
        return dummy.right;
    }
}
