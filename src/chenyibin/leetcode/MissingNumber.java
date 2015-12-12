package chenyibin.leetcode;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 * @author Yibin Chen
 */
public class MissingNumber {

    /**
     * The xor operation has the property that (x xor y xor y) = x
     * Create an xor number from all the indices and then re-xor
     * the contents of the array.
     * This should leave the one number that is missing
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums)
    {
        int result = 0;
        for (int i = 0; i <= nums.length; i++) {
            result ^= i;
        }
        
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}
