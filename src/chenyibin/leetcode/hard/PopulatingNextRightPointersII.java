package chenyibin.leetcode.hard;

import chenyibin.leetcode.common.TreeLinkNode;

public class PopulatingNextRightPointersII
{
    public void connect(TreeLinkNode root) {
        TreeLinkNode nextLevelLeftMost = root;
        
        while (nextLevelLeftMost != null)
        {
            TreeLinkNode curr = nextLevelLeftMost;
            TreeLinkNode prev = null;
            nextLevelLeftMost = null;
            // deal with one level at a time
            while (curr != null)
            {
                if (curr.left != null) {
                    if (prev == null) {
                        nextLevelLeftMost = curr.left;
                    } else {
                        prev.next = curr.left;
                    }
                    prev = curr.left;
                }
                if (curr.right != null) {
                    if (prev == null) {
                        nextLevelLeftMost = curr.right;
                    } else {
                        prev.next = curr.right;
                    }
                    prev = curr.right;
                }
                curr = curr.next;
            }
        }
    }
}
