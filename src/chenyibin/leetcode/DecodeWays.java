package chenyibin.leetcode;

/**
 * A message containing letters from A-Z is being encoded</br>
 * to numbers using the mapping:</br>
 * 'A' -> 1, 'B' -> 2, ... 'Z' -> 26
 * Given an encoded message, determine the total possible ways to decode it.
 *
 * @author Yibin Chen
 */
public class DecodeWays
{

	String code;

	public int numDecodings(String s)
	{
		int len = s.length();
		if (len == 0)
			return 0;

		this.code = s;

		// Build a dynamic programming table which records the number
		// of ways that we can decode s[i...(len-1)] at position i.
		int[] numDecode = new int[len + 1];

		// Array is one longer than the actual length to make loop easier
		numDecode[len] = 1;

		// We start at end of string
		int current = len - 1; 

		if (this.code.charAt(current) == '0') {
			// There is no way to decode '0'
			numDecode[current] = 0;
		} else {
			numDecode[current] = 1;
		}

		--current;
		for (;current >= 0; --current)
		{
			// If it's 0 there is no way to decode single character
			if (this.code.charAt(current) != '0') {
				// One way to decode is consider only the current digit
				numDecode[current] = numDecode[current + 1];
				if (canDecodeDoubleDigit(current)) {
					// Second way to decode is the consider two digits
					numDecode[current] += numDecode[current + 2];
				}
			}
		}

		return numDecode[0];
	}

	public boolean canDecodeDoubleDigit(int pos)
	{
		if (this.code.charAt(pos) > '2') {
			return false;
		}
		if (this.code.charAt(pos) == '1') {
			return true;
		}
		if (this.code.charAt(pos+1) > '6') {
			return false;
		}
		return true;
	}
}
