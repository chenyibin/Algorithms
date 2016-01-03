package chenyibin.leetcode;

/**
 * Problem #55 on leetcode.com:
 * Given an array of non-negative integers each element in the array
 * represents your maximum jump length at that position.
 * Determine if you are able to reach the last index from the first index. 
 * @author Yibin Chen
 */
public class JumpGame
{
    public boolean canJump(int[] nums) {
        int lastIndex = nums.length - 1;
        for (int i = lastIndex - 1; i >= 0; --i) {
            int reach = i + nums[i];
            if (reach >= lastIndex) {
                lastIndex = i;
            }
        }
        return (lastIndex == 0);
    }
}
