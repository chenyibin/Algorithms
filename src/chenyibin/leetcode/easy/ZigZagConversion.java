package chenyibin.leetcode.easy;

/**
 * Problem #6 on leetcode.com:
 * Read a string that is written in a zipzag pattern line by line.
 * @author Yibin Chen
 */
public class ZigZagConversion {

	public String convert(String s, int nRows) {
		if (nRows == 1) return s;

		StringBuilder result = new StringBuilder(s.length());
		int skippyOne = nRows + nRows;
		int skippyTwo = -2;
		int alwaysSkippy = skippyOne - 2;
		for (int o = 0; o < nRows; ++o)
		{
			skippyOne -= 2;
			skippyTwo += 2;
			boolean always = (skippyOne == 0) || (skippyTwo == 0);
			if (always) {
				for (int i = o; i < s.length(); i += alwaysSkippy) {
					result.append(s.charAt(i));
				}
			} else {
				boolean useOne = true;
				int i = o;
				while (i < s.length()) {
					result.append(s.charAt(i));
					if (useOne) {
						i += skippyOne;
						useOne = false;
					} else {
						i += skippyTwo;
						useOne = true;
					}
				}
			}
		}
		return result.toString();
	}
}
