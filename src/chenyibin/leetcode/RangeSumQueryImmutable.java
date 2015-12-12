package chenyibin.leetcode;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.
 * The array does not change and there are many calls to sumRange function.
 * 
 * @author Yibin Chen
 */
public class RangeSumQueryImmutable {
    
    public static class NumArray
    {
        // Store the partial sums from nums[0..i] 
        int[] partialSums;

        public NumArray(int[] nums)
        {
            partialSums = new int[nums.length];
            
            if (nums.length == 0) {
                return;
            }
            
            int prevIndex = 0;
            partialSums[0] = nums[0];
            for (int i = 1; i < nums.length; ++i) 
            {
                partialSums[i] = partialSums[prevIndex] + nums[i];
                prevIndex = i;
            }
        }

        public int sumRange(int i, int j) {
            if (i == 0) {
                return partialSums[j];
            }
            return partialSums[j] - partialSums[i-1];
        }
    }
}
