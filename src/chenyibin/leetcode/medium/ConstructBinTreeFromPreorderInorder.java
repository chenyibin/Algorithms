package chenyibin.leetcode.medium;

import java.util.Stack;

import chenyibin.leetcode.common.TreeNode;

public class ConstructBinTreeFromPreorderInorder
{
    public TreeNode buildTree(int[] preorder, int[] inorder)
    {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
        stack.push(dummy);
        
        TreeNode parent = null;
        int pre = 0;
        int in = 0;
        
        while (in < inorder.length)
        {
            if (stack.peek().val == inorder[in]) {
                parent = stack.pop();
                ++in;
            } else {
                TreeNode newNode = new TreeNode(preorder[pre++]);
                if (parent == null) {
                    stack.peek().left = newNode;
                } else {
                    parent.right = newNode;
                    parent = null;
                }
                stack.push(newNode);
            }
        }
        return dummy.left;
    }
    
}
