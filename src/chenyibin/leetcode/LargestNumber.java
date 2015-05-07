package chenyibin.leetcode;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Problem #179 from leetcode.com:</br>
 * Given a list of non negative integers, arrange them such that they form the largest number.</br>
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.</br>
 * The result may be very large, so you need to return a string instead of an integer.</br>
 * 
 * <strong>Solution:</strong></br>
 * Implement a comparator that puts the strings in the correct order and use a standard sorting algorithm.</br>
 * This simplifies the problem to: how to arrange two numbers in such a order such that the result is the largest.</br>
 * There is a fun and boring solution to making the comparator here.</br>
 * The boring solution (<code>BoringSetComparator.class</code>) is to concatenate the two strings, convert it to a long.</br>
 * The result is then to return the result of the comparison.</br>
 * The more fun solution (<code>MagicSetComparator.class</code>) will work with any length of string</br>
 * 
 * @author Yibin Chen
 */
public class LargestNumber
{
	private boolean useBoringSolution;
	public LargestNumber()
	{
		this.useBoringSolution = true;
	}
	
	/**
	 * <strong>Fun Solution:</strong></br>
	 * <p>
	 * Implement a comparator that puts the strings in the correct order without converting to <code>long</code>.</br>
	 * The comparator needs to order numbers by digit starting with their most significant digit.</br>
	 * The obvious cases:
	 * <li><i>9</i> needs to be ordered before <i>8</i>.</li>
	 * <li><i>97</i> needs to be ordered before <i>95</i>.</li>
	 * <li><i>234</i> needs to be ordered before <i>13</i>.</li>
	 * </p>
	 * <p>
	 * Problems arise when we have a case where a number is a prefix of another number:</br>
	 * For instance how do we compare <i>533</i> with <i>53353356</i>?</br>
	 * The answer is that the solution is the same answer as if we compared <i>533</i> with <i>56</i>.</br>
	 * What about <i>4543</i> with <i>4543454</i>?</br>
	 * The answer is that the solution is the same answer as if we compared <i>3</i> with <i>454</i>.</br>
	 * </p>
	 * <p>
	 * If two numbers have the same prefix, then resulting largest number must start with that prefix.</br>
	 * So the solution is obtained by removing the prefix on the longer string and comparing the remainder to the prefix.</br>
	 * Now it is possible that the remainder now is the prefix of the previously smaller string as in our second example.</br>
	 * So we repeat the process until neither of the strings is a prefix of the other.</br>
	 * </p>
	 */
	private static class MagicSetComparator implements Comparator<String>
	{
		@Override
		public int compare(String o1, String o2)
		{
			boolean o1StartsWitho2 = false;
			boolean o2StartsWitho1 = false;
			do {
				o1StartsWitho2 = false;
				o2StartsWitho1 = false;
				if (o1.length() > o2.length()) {
					o1StartsWitho2 = o1.startsWith(o2);
					if (o1StartsWitho2) {
						o1 = o1.substring(o2.length());
					}
				} else if (o2.length() > o1.length()) {
					o2StartsWitho1 = o2.startsWith(o1);
					if (o2StartsWitho1) {
						o2 = o2.substring(o1.length());
					}
				} else if (o1.equals(o2)) {
					// We might be corrupting the set by allowing same strings
					// but that's okay because we're solving the problem and
					// we don't care about removing from the set anyways
					return 1;
				}
			} while (o1StartsWitho2 || o2StartsWitho1);

			int looplen = Math.max(o1.length(), o2.length());
			for (int i = 0; i < looplen; ++i) {
				if (i >= o1.length() || i >= o2.length()) {
					throw new RuntimeException("prefix elimination should make this case impossible");
				}

				if (o1.charAt(i) < o2.charAt(i)) {
					return 1;
				} else if (o1.charAt(i) > o2.charAt(i)) {
					return -1;
				}
			}
			
			 // this is impossible, but java doesn't know that
			return 0;
		}

	}
	
	/**
	 * Boring Solution
	 */
	private static class BoringSetComparator implements Comparator<String>
	{
		@Override
		public int compare(String o1, String o2) {

			Long o1o2 = Long.parseLong(o1.concat(o2));
			Long o2o1 = Long.parseLong(o2.concat(o1));
			
			int numCompare = o2o1.compareTo(o1o2);
			if (numCompare == 0) {
				// We might be corrupting the set by allowing same strings
				// but that's okay because we're solving the problem and
				// we don't care about removing from the set anyways
				return 1;
			}
			return numCompare;
		}
	}

	public String largestNumber(int[] nums)
	{
		StringBuilder result = new StringBuilder();

		SortedSet<String> largeNumBuilder = this.useBoringSolution ?
			new TreeSet<String>(new BoringSetComparator()) :
			new TreeSet<String>(new MagicSetComparator());

		for (int i : nums)
		{
			largeNumBuilder.add(Integer.toString(i));
		}

		if (largeNumBuilder.first().equals("0")) {
			return "0";
		}

		for (String s : largeNumBuilder)
		{
			result.append(s);
		}

		return result.toString();
	}

	public boolean isUseBoringSolution() {
		return useBoringSolution;
	}

	public void setUseBoringSolution(boolean useBoringSolution) {
		this.useBoringSolution = useBoringSolution;
	}
}
