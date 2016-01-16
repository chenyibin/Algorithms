package chenyibin.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import chenyibin.leetcode.common.TreeNode;

public class BinaryTreeLevelOrderII {
    public List<List<Integer>> levelOrderBottom(TreeNode root)
    {
        if (root == null) {
            return Collections.emptyList();
        }
        
        LinkedList<List<Integer>> result = new LinkedList<>();

        Queue<TreeNode> traversalQueue = new LinkedList<>();
        traversalQueue.add(root);
        while (!traversalQueue.isEmpty())
        {
            int levelSize = traversalQueue.size();
            List<Integer> level = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; ++i)
            {
                TreeNode current = traversalQueue.poll();
                level.add(current.val);
                if (current.left != null) {
                    traversalQueue.add(current.left);
                }
                if (current.right != null) {
                    traversalQueue.add(current.right);
                }
            }
            result.addFirst(level);
        }
        return result; 
    }
}
