package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem #77 on leetcode.com:</br>
 * Given two integers n and k,</br>
 * return all possible combinations of k numbers out of 1 ... n.
 * 
 * @author Yibin Chen
 */
public class CombineCombinations
{
	private static class Generator
	{
		int n;
		int k;
		int[] combo;

		Generator(int n, int k)
		{
			this.n = n;
			this.k = k;
			this.combo = new int[k];
			for (int i = 0; i < k; ++i)
			{
				combo[i] = i + 1;
			}
		}

		List<Integer> get() {
			List<Integer> newList = new ArrayList<Integer>();
			for (int i : combo)
				newList.add(i);
			return newList;
		}

		public boolean increment() {

			int incremented = Integer.MIN_VALUE;
			
			// 'slide' the last index with increment room:
			// So for instance with k = 4 and n = 7 if combo is
			// [1,3,6,7] then 3 is the last index that can be incremented
			// to 4 since 7 is the largest number and 6 cannot be incremented
			// to 7 since it already exists.
			for (int i = k - 1; i >= 0; --i)
			{
				if (combo[i] == n) {
					continue;
				}
				if (i != k - 1 && (combo[i] == combo[i+1] - 1))  {
					continue;
				}
				++combo[i];
				incremented = i;
				break;
			}

			if (incremented == Integer.MIN_VALUE)
			{
				return false;
			}
			else
			{
				// Reset everything after the index we just incremented:
				// So for our previous example after we've incremented 3 -> 4
				// we reset the remainder of the array so the result looks like
				// [1,4,5,6]. On the next increment we'll change 6 to 7
				for (int j = incremented + 1; j < combo.length; ++j)
				{
					combo[j] = combo[j-1] + 1;
				}
			}
			return true;
		}
	}

	public List<List<Integer>> combine(int n, int k) {

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (n < k) {
			throw new IllegalArgumentException("n cannot be less than k");
		}

		Generator gen = new Generator(n, k);
		do {
			List<Integer> combo = gen.get();
			result.add(combo);
		} while (gen.increment());
		return result;
	}
}
