package chenyibin.leetcode.medium;

/**
 * Problem #260 on leetcode.com:
 * Given an array of numbers, in which exactly two elements
 * appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once. 
 * @author Yibin Chen
 */
public class SingleNumberIII {
    public int[] singleNumber(int[] nums)
    {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int firstBit = xor & -xor;
        
        int groupSet = 0;
        int groupUnset = 0;
        for (int num : nums) {
            if ((num & firstBit) == 0) {
                groupUnset ^= num;
            } else {
                groupSet ^= num;
            }
        }
        return new int[] {groupUnset, groupSet};
    }
}
