package chenyibin.leetcode.easy;

import java.util.LinkedList;
import java.util.Queue;

import chenyibin.leetcode.common.TreeNode;

public class MinimumDepthBinaryTree
{
    public int minDepth(TreeNode root)
    {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> traversalQueue = new LinkedList<>();
        traversalQueue.add(root);
        int depth = 0;
        while (!traversalQueue.isEmpty()) {
            int levelSize = traversalQueue.size();
            
            for (int i = 0; i < levelSize; ++i) {
                TreeNode current = traversalQueue.poll();
                boolean isLeaf = true;
                if (current.left != null) {
                    traversalQueue.add(current.left);
                    isLeaf = false;
                }
                
                if (current.right != null) {
                    traversalQueue.add(current.right);
                    isLeaf = false;
                }
                if (isLeaf) {
                    return depth;
                }
            }
            ++depth;
        }
        return depth;
    }
}
