package chenyibin.leetcode;

/**
 * Problem #189 on leetcode.com:</br>
 * 
 * @author Yibin Chen
 */
public class RotateArray {

	public void rotate(int[] nums, int k) {
		int lastIndex = nums.length - 1;
		k %= nums.length;
		int kIndex = k - 1;
		reverseArray(nums, 0, lastIndex);
		reverseArray(nums, 0, kIndex);
		reverseArray(nums, k, lastIndex);
	}

	private void reverseArray(int[] nums, int start, int end)
	{
		while (start < end) {
			// swap without temp
			nums[start] ^= nums[end];
			nums[end] ^= nums[start];
			nums[start] ^= nums[end];
			++start;
			--end;
		}
	}
}
