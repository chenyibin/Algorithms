package chenyibin.leetcode;

import java.util.Deque;
import java.util.LinkedList;

import chenyibin.leetcode.common.TreeNode;

public class MaxDepthOfBinaryTree
{
    public int maxDepth(TreeNode root)
    {
        if (root == null) {
            return 0;
        }
        // traversal queue
        Deque<TreeNode> tq = new LinkedList<>();

        tq.add(root);

        int maxDepth = 0;
        
        while (!tq.isEmpty()) {
            ++maxDepth;
            int levelSize = tq.size();
            for (int i = 0; i < levelSize; ++i) {
                TreeNode node = tq.poll();
                if (node.left != null) {
                    tq.offer(node.left);
                }
                if (node.right != null) {
                    tq.offer(node.right);
                }
            }
        }
        
        return maxDepth;
    }
}
