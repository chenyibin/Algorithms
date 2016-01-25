package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem #300 from leetcode.com:
 * Given an unsorted array of integers,
 * find the length of longest increasing subsequence.
 * 
 * @author Yibin Chen
 */
public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums)
    {
        if (nums.length == 0) {
            return 0;
        }
        List<Integer> bestSequence = new ArrayList<>();
        bestSequence.add(nums[0]);

        for (int i = 1; i < nums.length; ++i)
        {
            int newCard = nums[i];
            int leftmostGreaterPile = bestSequence.size() - 1;
            if (bestSequence.get(leftmostGreaterPile) < newCard) {
                bestSequence.add(newCard);
                continue;
            }
            --leftmostGreaterPile;
            while (leftmostGreaterPile >= 0
             && bestSequence.get(leftmostGreaterPile) >= newCard)
            {
                --leftmostGreaterPile;
            }
            bestSequence.set(++leftmostGreaterPile, newCard);
        }
        
        return bestSequence.size();
    }
}
