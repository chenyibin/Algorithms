package chenyibin.leetcode;

/**
 * Problem #45 on leetcode.com:
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps. 
 * @author Yibin Chen
 */
public class JumpGameII
{
    public int jump(int[] nums)
    {
        int jumps = 0;
        int maxReach = 0;
        int nextMaxReach = 0;
        
        for (int i = 0; i <= maxReach && i < nums.length - 1; ++i) {
            int reachFromHere = i + nums[i];
            nextMaxReach = Math.max(nextMaxReach, reachFromHere);
            if (i == maxReach) {
                ++jumps;
                maxReach = nextMaxReach;
            }
        }
        if (maxReach < nums.length - 1) {
            return -1;
        }
        return jumps;
    }
}
