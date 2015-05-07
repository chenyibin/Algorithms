package chenyibin.leetcode;

/**
 * Problem #31 on leetcode.com:
 * Rearrange numbers into the lexicographically next greater permutation of numbers.</br>
 * If such arrangement is not possible, rearrange it as the lowest possible order.</br>
 * The replacement must be in-place.</br>
 * 
 * @author Yibin Chen
 */
public class NextPermutation
{

	public void nextPermutation(int[] nums)
	{
		if (nums.length < 2) {
			return;
		}
		
		int trailingIndex = nums.length - 1;
		int seeker = trailingIndex - 1;
		for (; seeker >= 0; --seeker) 
		{
			// Find first number which is less than previous
			if (nums[seeker] < nums[trailingIndex]) {
				break;
			}
			trailingIndex = seeker;
		}
		// reverse everything after that number
		reverse(nums, trailingIndex);
		
		// if everything was reverse return
		if (seeker < 0) {
			return;
		}
		
		// swap the number with next largest in trailer
		// the trailer will still be sorted
		for (int i = trailingIndex; i < nums.length; ++i)
		{
			if (nums[seeker] < nums[i]) {
				swap(nums, seeker, i);
				break;
			}
		}
	}

	private void reverse(int[] nums, int start) {
		int end = nums.length - 1;
		while (start < end) {
			swap(nums, start, end);
			++start;
			--end;
		}
	}
	
	private void swap(int[] nums, int i, int j)
	{
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
