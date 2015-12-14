package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem #228 on leetcode.com:</br>
 * 
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * 
 * @author Yibin Chen
 */
public class SummaryRanges {

    public List<String> summaryRanges(int[] nums)
    {
        List<String> result = new ArrayList<>();
        
        int i = 0;
        while (i < nums.length)
        {
            int counter = nums[i];
            
            int start = i;

            int end = i;
            ++i;
            while (i < nums.length && nums[i] == counter + 1)
            {
                counter = nums[i];
                end = i;
                ++i;
            }
            if (i == start + 1) {
                result.add(String.valueOf(nums[start]));
            } else {
                result.add(String.valueOf(nums[start]) + "->"
                    + String.valueOf(nums[end]));
            }
        }
        return result;
    }
}
