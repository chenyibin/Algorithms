package chenyibin.leetcode;

/**
 * Problem #41 from leetcode.com:</br>
 * Given an unsorted integer array, find the first missing positive integer.
 * Do it in O(1) space and O(n) time. The input array may be edited.
 * For instance given [3,4,-1,1] return 2, given [1,2] return 3, and given [1,100] return 2.
 * 
 * @author Yibin Chen
 */
public class FirstMissingPositive
{
	public int firstMissingPositive(int[] nums)
	{
		if (nums.length == 0)
			return 1;
			
		for (int i = 0; i < nums.length; ++i)
		{
			// Move things into their 'right' place by
			// swapping until we can no longer do it.
			// That is each nums[i] should contain (i+1).
			// We continue swapping until either nums[i]
			// is out of range or because the number we are
			// swapping with is the same as nums[i]
			while (nums[i] > 0 && nums[i] < nums.length)
			{
				int num     = nums[i];
				int destNum = nums[num-1];
				if (num == destNum)
					break;
				nums[num-1] = num;
				nums[i]     = destNum;
			}
		}
		
		// The first integer that is out of place is our result
		int seek = 0;
		while (seek < nums.length )
		{
			// abuse operator precedence in favor of readability
			if (nums[seek] != ++seek) {
				return seek;
			}
		}
		// Otherwise it's one past the highest number 
		return seek + 1;
	}
}
