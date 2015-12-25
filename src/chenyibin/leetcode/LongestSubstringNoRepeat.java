package chenyibin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #3 on leetcode.com:
 * Given a string, find the length of the longest substring without repeating characters.
 * 
 * @author Yibin Chen
 */
public class LongestSubstringNoRepeat {

	public int lengthOfLongestSubstring(String s) {
		Map<Character, Integer> memory = new HashMap<Character, Integer>(256);
		int max = 0;
		int start = 0;
		for (int i = 0; i < s.length(); ++i)
		{
			Character c = s.charAt(i);
			Integer found = memory.get(c);
			if (found != null && found >= start)
			{
				max = Math.max(max, i - start);
				start = found + 1;
			}
			memory.put(c, i);
		}
		max = Math.max(max, s.length() - start);
		return max;
	}
}
