package chenyibin.leetcode;

/**
 * Problem #35 on leetcode.com:
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 * @author Yibin Chen
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start < end) {
            int mid = (start + end) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        if (nums[start] < target) {
            return start + 1;
        }
        return start;
    }
}
