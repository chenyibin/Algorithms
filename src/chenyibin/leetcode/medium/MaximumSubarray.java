package chenyibin.leetcode.medium;

/**
 * 
 * @author Yibin Chen
 */
public class MaximumSubarray
{

    public int maxSubArray(int[] nums)
    {
        int max = Integer.MIN_VALUE;
        int maxTillHere = 0;
        for (int num : nums)
        {
            if (maxTillHere < 0 && maxTillHere < num) {
                maxTillHere = num;
            } else {
                maxTillHere += num;
            }
            max = Math.max(max, maxTillHere);
        }
        return max;
    }
}
