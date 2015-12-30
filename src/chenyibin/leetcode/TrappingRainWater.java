package chenyibin.leetcode;

/**
 * Problem #42 on leetcode.com:
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining. 
 * @author Yibin Chen
 */
public class TrappingRainWater {

    public int trap(int[] height)
    {
        int maxRight = 0;
        int maxLeft = 0;
        int left = 0;
        int right = height.length - 1;
        int water = 0;
        
        while (left <= right) {
            int leftHeight = height[left];
            int rightHeight = height[right];
            if (leftHeight <= rightHeight) {
                if (leftHeight < maxLeft) {
                    water += (maxLeft - leftHeight);
                } else {
                    maxLeft = leftHeight;
                }
                ++left;
            } else {
                if (rightHeight < maxRight) {
                    water += (maxRight - rightHeight);
                } else {
                    maxRight = rightHeight;
                }
                --right;
            }
        }
        return water;
    }
}
