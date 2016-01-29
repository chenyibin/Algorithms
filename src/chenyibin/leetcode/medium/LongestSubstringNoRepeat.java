package chenyibin.leetcode.medium;

import java.util.Arrays;

/**
 * Problem #3 on leetcode.com:
 * Given a string, find the length of the longest substring
 * without repeating characters.
 * 
 * @author Yibin Chen
 */
public class LongestSubstringNoRepeat
{

    public int lengthOfLongestSubstring(String s)
    {
        int[] memory = new int[256];
        Arrays.fill(memory, -1);
        
        int max = 0;
        int start = 0;
        for (int i = 0; i < s.length(); ++i)
        {
            char c = s.charAt(i);
            int found = memory[c];
            if (found >= start)
            {
                max = Math.max(max, i - start);
                start = found + 1;
            }
            memory[c] = i;
        }
        max = Math.max(max, s.length() - start);
        return max;
    }
}
