package chenyibin.leetcode.medium;

import java.util.LinkedList;
import java.util.Queue;

import chenyibin.leetcode.common.TreeLinkNode;

public class PopulatingNextRightPointers
{
    public void connect(TreeLinkNode root)
    {
        if (root == null) {
            return;
        }
        Queue<TreeLinkNode> tq = new LinkedList<>();
        tq.add(root);
        
        while (!tq.isEmpty())
        {
            int qSize = tq.size();
            boolean first = true;
            TreeLinkNode prev = null;
            for (int i = 0; i < qSize; ++i) {
                TreeLinkNode current = tq.poll();
                if (first) {
                    first = false;
                } else {
                    prev.next = current;
                }
                if (current.left != null) {
                    tq.add(current.left);
                }
                if (current.right != null) {
                    tq.add(current.right);
                }
                prev = current;
            }
        }
    }
}
