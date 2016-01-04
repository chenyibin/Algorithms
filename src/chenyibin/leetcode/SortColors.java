package chenyibin.leetcode;

/**
 * Problem #75 on leetcode.com:
 * Given an array with n objects colored red (0), white (1) or blue (2),
 * sort them in order red, white, and blue.
 * @author Yibin Chen
 */
public class SortColors
{
    int[] nums;
    
    public void sortColors(int[] nums)
    {
        this.nums = nums;
        
        int redIndex = 0;
        int whiteIndex = 0;
        int blueIndex = nums.length - 1;
        while (whiteIndex <= blueIndex) {
            if (nums[whiteIndex] == 0) {
                swap(whiteIndex, redIndex);
                ++whiteIndex;
                ++redIndex;
            } else if (nums[whiteIndex] == 2) {
                swap(whiteIndex, blueIndex);
                --blueIndex;
            } else {
                ++whiteIndex;
            }
        }
    }
    
    public void swap(int i1, int i2) {
        if (i1 == i2) return;
        int temp = this.nums[i1];
        this.nums[i1] = this.nums[i2];
        this.nums[i2] = temp;
    }
}
