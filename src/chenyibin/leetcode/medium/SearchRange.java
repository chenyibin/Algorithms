package chenyibin.leetcode.medium;

public class SearchRange {

    int[] nums;
    int target;
    
    public int[] searchRange(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        int l = searchLeft();
        int r = searchRight(l);
        
        int[] result = {l, r};
        return result;
    }
    
    private int searchLeft()
    {
        int lBound = -1;
        int rIndex = nums.length - 1;
        while (lBound + 1 < rIndex)
        {
            int mid = (lBound + rIndex) >> 1;
            int midVal = nums[mid];
            if (target <= midVal) {
                rIndex = mid;
            } else {
                lBound = mid;
            }
        }
        if (nums[rIndex] != target) {
            return -1;
        }
        return rIndex;
    }
    
    private int searchRight(int begin)
    {
        int lIndex = 0;
        int rBound = nums.length;
        while (lIndex + 1 < rBound)
        {
            int mid = (lIndex + rBound) >> 1;
            int midVal = nums[mid];
            if (target >= midVal) {
                lIndex = mid;
            } else {
                rBound = mid;
            }
        }
        if (nums[lIndex] != target) {
            return -1;
        }
        return lIndex;
    }
}
