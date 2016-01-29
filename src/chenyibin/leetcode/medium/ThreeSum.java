package chenyibin.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #15 on leetcode.com:</br>
 * Given an array <i>S</i> of <i>n</i> integers, are there elements <i>a, b, c</i></br>
 * in <i>S</i> such that a + b + c = 0? Find all unique triplets.</br>
 * <li>Elements in a triplet (a,b,c) must be in non-descending order. (i.e. a <= b <= c)</li>
 * <li>The solution set must not contain duplicate triplets.</li>
 *
 * @author Yibin Chen
 */
public class ThreeSum
{

    public List<List<Integer>> threeSum(int[] nums)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (nums.length < 3) {
            return result;
        }
        
        Arrays.sort(nums);
        
        int lastIndex = nums.length - 1;
        int prev = 0;
        for (int first = 0; first < nums.length - 2; ++first)
        {
            int firstNum = nums[first];
            if (first != 0 && firstNum == nums[prev]) {
                continue;
            }
            prev = first;
            
            // Do Two Sum
            int left = first + 1;
            int right = lastIndex;
            int targetTwoSum = -firstNum;
            while (left < right)
            {
                int leftNum = nums[left];
                int rightNum = nums[right];
                int leftAndRight = leftNum + rightNum;
                if (leftAndRight == targetTwoSum)
                {
                    result.add(Arrays.asList(firstNum, leftNum, rightNum));
                    while (left < right && leftNum == nums[++left]);
                    while (left < right && rightNum == nums[--right]);
                }
                else if (leftAndRight < targetTwoSum)
                {
                    ++left;
                }
                else
                {
                    --right;
                }
            }
            
        }
        
        return result;
    }
}
