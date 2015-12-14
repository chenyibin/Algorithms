package chenyibin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #28 on leetcode.com:</br>
 * 
 * Returns the index of the first occurrence of needle in haystack,</br>
 * or -1 if needle is not part of haystack. </br>
 * 
 * <strong>Solution:</strong></br>
 * KMP is pretty complicated so let's just do Horspools' algorithm
 * 
 * @author Yibin Chen
 */
public class StrStrImplementation
{
	
	public static class Pattern
	{
		// When searching, the goal of the bad character shift is to
		// 'line-up' the char at the front of the haystack that we are
		// currently matching against with the same character in the needle.
		// For instance given pattern "adbbb", the skip lenght for 'd' is 3:
		// "aaabadddcaacdcee"
		// before: "adbbb"
		// after:     "adbbb"
		private Map<Character, Integer> badCharSkip;
		private int patternLen;
		private int lastIndex;
		
		public Pattern(String pattern)
		{
			this.badCharSkip = new HashMap<Character, Integer>();
			this.patternLen = pattern.length();
			this.lastIndex = patternLen - 1;
			for (int i = 0; i < this.lastIndex; ++i) {
				badCharSkip.put(pattern.charAt(i), this.lastIndex - i);
			}
		}
		
		int getSkipLength(Character c)
		{
			Integer skipLength = this.badCharSkip.get(c);
			if (skipLength == null)
				return patternLen;
			return skipLength;
		}
	}
	
	public int strStr(String haystack, String needle)
	{
		if (needle.isEmpty())
			return 0;

		if (haystack.isEmpty())
			return -1;
		
		Pattern pat = new Pattern(needle);
		
		int seeker = needle.length() - 1;
		while (seeker < haystack.length())
		{
			// we're matching from tip of needle to head
			for (int needleI= pat.lastIndex, hayI = seeker;
				needleI >= 0; --needleI, --hayI)
			{
				if (needle.charAt(needleI) != haystack.charAt(hayI)) {
					break;
				}
				if (needleI == 0) {
					return hayI;
				}
			}
			
			char seekerChar = haystack.charAt(seeker);
			seeker += pat.getSkipLength(seekerChar);
		}
		return -1;
	}

}
