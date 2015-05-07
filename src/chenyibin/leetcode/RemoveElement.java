package chenyibin.leetcode;

/**
 * Problem #27 from leetcode.com:</br>
 * Given an array and a value, remove all instances of that value in place</br>
 * and return the new length. The order of elements can be changed.</br>
 * 
 * @author Yibin Chen
 */
public class RemoveElement
{
	public int removeElement(int[] nums, int val) {
		if (nums.length == 0)
			return 0;
			
		int removed = 0;
		int left = 0;
		int right = nums.length - 1;
		
		while (left <= right)
		{
			// remove stuff from end to ensure that end value isn't
			// the value we're trying to remove
			if (nums[right] == val)
			{
				--right;
				++removed;
				continue;
			}
			// if the left value matches val replace it with the
			// element at the end which is guaranteed to not be val
			if (nums[left] == val) {
				nums[left] = nums[right];
				--right;
				++removed;
			}
			++left;
		}
		
		return nums.length - removed;
	}
}
