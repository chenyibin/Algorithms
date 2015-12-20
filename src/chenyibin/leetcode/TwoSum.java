package chenyibin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #1 on leetcode.com:
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * 
 * @author Yibin Chen
 */
public class TwoSum
{
	public int[] twoSum(int[] numbers, int target)
	{
		Map<Integer, Integer> toIndex = new HashMap<Integer, Integer>(numbers.length);
		for (int i = 0; i < numbers.length; ++i) {
			int current = numbers[i];
			Integer found = toIndex.get(current);
			if (found != null) {
				return new int[] {found + 1, i + 1};
			}
			int diff = target - current;
			toIndex.put(diff, i);
		}

		return null;
	}
}
