package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

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
	List<List<Integer>> result;

	public List<List<Integer>> threeSum(int[] num)
	{
		this.result = new LinkedList<List<Integer>>();

		if (num.length < 3)
			return result;

		// Since the solution must be sorted we might as well
		// sort the input and store the frequency of each number
		SortedMap<Integer,Integer> input = new TreeMap<Integer,Integer>();

		for (Integer i : num) {
			Integer already = input.get(i);
			if (already == null)
				input.put(i, 1);
			else
				input.put(i, already + 1);
		}

		for (Entry<Integer,Integer> oneEntry : input.entrySet())
		{
			for (Entry<Integer,Integer> twoEntry : input.entrySet())
			{
				int key1 = oneEntry.getKey();
				int key2 = twoEntry.getKey();
				if (key2 < key1) {
					continue;
				}

				boolean key1EqKey2 = (key1 == key2);
				if (key1EqKey2 && oneEntry.getValue() < 2) {
					continue;
				}

				int needed = 0 - key1 - key2;
				if (needed < key2) continue;
				if (input.containsKey(needed)) {
					boolean countAccepted = true;
					if (needed == key1) {
						if (key1EqKey2) {
							countAccepted = oneEntry.getValue() > 2;
						} else {
							countAccepted = oneEntry.getValue() > 1;
						}
					} else if (needed == key2) {
						countAccepted = twoEntry.getValue() > 1;
					}

					if (countAccepted) {
						addResult(key1, key2, needed);
					}
				}
			}
		}

		return result;
	}

	private void addResult(Integer first, Integer second, Integer third) {
		List<Integer> triplet = new ArrayList<Integer>(3);
		triplet.add(first);
		triplet.add(second);
		triplet.add(third);
		result.add(triplet);
	}
}
