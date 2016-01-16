package chenyibin.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import chenyibin.leetcode.common.TreeNode;

public class PathSumII
{
    List<List<Integer>> answer;
    int sum;
    
    public List<List<Integer>> pathSum(TreeNode root, int sum)
    {
        if (root == null) {
            return Collections.emptyList();
        }
        this.answer = new ArrayList<>();
        this.sum = sum;
        LinkedList<Integer> path = new LinkedList<>();
        recurse(root, path, 0);
        
        return this.answer;
    }

    private void recurse(TreeNode node, LinkedList<Integer> path, int partialSum)
    {
        partialSum += node.val;
        path.addLast(node.val);
        boolean isLeaf = true;
        if (node.left != null) {
            recurse(node.left, path, partialSum);
            isLeaf = false;
        }
        if (node.right != null) {
            recurse(node.right, path, partialSum);
            isLeaf = false;
        }
        
        if (isLeaf && partialSum == this.sum)
        {
            this.answer.add(new ArrayList<>(path));
        }
        path.removeLast();
    }
}
