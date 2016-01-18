package chenyibin.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #128 on leetcode.com:
 * Given an unsorted array of integers,
 * find the length of the longest consecutive elements sequence.
 * @author Yibin Chen
 */
public class LongestConsecutiveSequence
{
    public int longestConsecutive(int[] nums)
    {
        Map<Integer, Integer> sequenceLengths = new HashMap<>();
        int maxLen = 0;
        for (int num : nums) {
            if (sequenceLengths.containsKey(num)) {
                continue;
            }
            int sequenceLength = 1;
            Integer right = sequenceLengths.get(num + 1);
            if (right != null) {
                sequenceLength += right;
            }
            Integer left = sequenceLengths.get(num - 1);
            if (left != null) {
                sequenceLength += left;
            }
            sequenceLengths.put(num, sequenceLength);
            if (right != null) {
                sequenceLengths.put(num + right, sequenceLength);
            }
            if (left != null) {
                sequenceLengths.put(num - left, sequenceLength);
            }
            maxLen = Math.max(sequenceLength,maxLen);
        }
        
        return maxLen;
    }
}
