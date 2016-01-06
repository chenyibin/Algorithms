package chenyibin.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

import chenyibin.leetcode.common.TreeNode;

/**
 * Problem #199 from leetcode.com:
 * Given a binary tree, imagine yourself standing on the right side of it.
 * Return the values of the nodes you can see ordered from top to bottom.
 * @author Yibin Chen
 */
public class BinaryTreeRightSideView
{
    int lowest;
    List<Integer> result;
    
    public List<Integer> rightSideView(TreeNode root)
    {
        this.lowest = 0;
        this.result = new ArrayList<>(100);
        buildRightSideView(root, 0);
        return this.result;
    }

    private void buildRightSideView(TreeNode node, int level)
    {
        if (node == null) {
            return;
        }
        if (level == this.lowest) {
            this.result.add(node.val);
            ++this.lowest;
        }
        int nextLevel = level + 1;
        buildRightSideView(node.right, nextLevel);
        buildRightSideView(node.left, nextLevel);
    }
}
