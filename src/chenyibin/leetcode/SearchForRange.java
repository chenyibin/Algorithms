package chenyibin.leetcode;

/**
 * Problem #34 on leetcode.com:
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * 
 * @author Yibin Chen
 */
public class SearchForRange {

    public int[] searchRange(int[] nums, int target)
    {
        int[] range = {-1,-1};
        
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (target > nums[mid]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (nums[start] != target) {
            return range;
        }
        range[0] = start;
        
        // note that we don't have to reset the start
        end = nums.length - 1;
        while (start < end) {
            // round up instead of down
            int mid = (start + end + 1) >>> 1;
            if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }
        range[1] = end;
        
        return range;
    }
}
