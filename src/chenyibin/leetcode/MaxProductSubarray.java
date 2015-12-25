package chenyibin.leetcode;

/**
 * Problem #152 on leetcode.com:
 * Find the contiguous subarray of size > 0 within an array which has the largest product.
 * @author Yibin Chen
 */
public class MaxProductSubarray {

    public int maxProduct(int[] nums) {
        
        if (nums.length == 0) {
            return 0;
        }
        
        int maxProdTillHere = nums[0];
        int minProdTillHere = nums[0];
        int maxProd = maxProdTillHere;
        
        for (int i = 1; i < nums.length; ++i) {
            int current = nums[i];
            int currentMinProd = minProdTillHere * current;
            int currentMaxProd = maxProdTillHere * current;
            if (current < 0) {
                minProdTillHere = Math.min(current, currentMaxProd);
                maxProdTillHere = Math.max(current, currentMinProd);
            } else {
                minProdTillHere = Math.min(current, currentMinProd);
                maxProdTillHere = Math.max(current, currentMaxProd);
            }
            maxProd = Math.max(maxProd, maxProdTillHere);
        }
        
        return maxProd;
    }
}
