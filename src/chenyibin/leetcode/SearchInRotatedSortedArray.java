package chenyibin.leetcode;

public class SearchInRotatedSortedArray
{
    int[] nums;
    int target;
    
    public int search(int[] nums, int target)
    {
        this.nums = nums;
        this.target = target;
        
        int realMinIndex = findMinIndex();
        
        if (target == nums[realMinIndex]) {
            return realMinIndex;
        }
        
        int lastIndex = nums.length - 1;
        int lastVal = nums[lastIndex];
        int lo, hi;
        if (target == lastVal) {
            return lastIndex;
        } else if (target < lastVal) {
            lo = realMinIndex;
            hi = lastIndex;
        } else {
            lo = 0;
            hi = realMinIndex - 1;
        }
        
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        
        return -1;
    }

    private int findMinIndex()
    {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
