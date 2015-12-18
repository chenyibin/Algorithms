package chenyibin.leetcode;

import java.util.OptionalInt;
import java.util.Set;

/**
 * Problem #139 on leetcode.com:
 * Given a string s and a dictionary of words dict,
 * determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * 
 * @author Yibin Chen
 */
public class WordBreak
{
    public boolean wordBreak(String s, Set<String> wordDict)
    {
        OptionalInt maybeMax = wordDict.stream()
            .mapToInt(String::length)
            .max();
        
        if (!maybeMax.isPresent()) {
            return false;
        }
        
        int maxLen = maybeMax.getAsInt();
        
        boolean[] breakable = new boolean[s.length() + 1];
        
        breakable[0] = true;

        for (int left = 0; left < s.length(); ++left)
        {
            if (!breakable[left]) {
                continue;
            }
            int maxRight = Math.min(s.length(), left + maxLen);
            for (int right = left + 1; right <= maxRight; ++right)
            {
                if (wordDict.contains(s.substring(left, right)))
                {
                    breakable[right] = true;
                }
            }
        }
        
        return breakable[s.length()];
    }
}
