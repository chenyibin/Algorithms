package chenyibin.leetcode;

import chenyibin.leetcode.common.TreeNode;

/**
 * Problem #108 from leetcode.com:
 * Convert a sorted array into a binary search tree.
 * 
 * @author Yibin Chen
 */
public class ConvertSortedArrayToBST
{
    int[] nums;
    
    public TreeNode sortedArrayToBST(int[] nums)
    {
        this.nums = nums;
        return recurse(0, nums.length-1);
    }

    private TreeNode recurse(int start, int end)
    {
        if (start > end) {
            return null;
        }
        int mid = (start + end) >>> 1;
        
        TreeNode node = new TreeNode(nums[mid]);
        node.left = recurse(start, mid - 1);
        node.right = recurse(mid + 1, end);
        return node;
    }
}
