package chenyibin.leetcode;

public class ProductOfArrayExceptSelf
{
    public int[] productExceptSelf(int[] nums)
    {
        int[] output = new int[nums.length];
        
        output[0] = nums[0];
        int prevIndex = 0;
        for (int i = 1; i < nums.length; ++i) {
            output[i] = nums[i] * output[prevIndex];
            prevIndex = i;
        }
        
        int lastIndex = nums.length - 1;
        int trailingProduct = 1;
        int trailingIndex = lastIndex;
        for (int i = trailingIndex - 1; i > 0; --i)
        {
            output[trailingIndex] = output[i] * trailingProduct;
            trailingProduct *= nums[trailingIndex];
            trailingIndex = i;
        }
        output[0] = trailingProduct;
        
        return output;
    }
}
