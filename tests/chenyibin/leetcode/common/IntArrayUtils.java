package chenyibin.leetcode.common;

public class IntArrayUtils {

	public String stringify(int[] intarr)
	{
		StringBuilder result = new StringBuilder();
		result.append("[");
		boolean first = true;
		for (int i = 0; i < intarr.length; ++i)
		{
			if (first)
				first = false;
			else
				result.append(",");
			result.append(intarr[i]);
		}
		result.append("]");
		
		return result.toString();
	}
}
