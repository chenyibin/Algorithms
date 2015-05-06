package chenyibin.leetcode;

/**
 * Problem #162 from leetcode.com:
 * A peak element is an element that is greater than its neighbors.</br>
 * Given an input array where num[i] != num[i+1], find a peak element and return its index.</br>
 * If the array contains multiple peaks, return the index to any one of the peaks.</br>
 * nums[-1] and nums[len] are considered to be negative infinity</br>
 * 
 * @author Yibin Chen
 */
public class FindPeakElement {

    public int findPeakElement(int[] nums)
    {
    	int len = nums.length;
    	if (len == 0) {
    		return -1;
    	}
    	if (len == 1) {
    		return 0;
    	}
    	
    	// Check end points
    	int lastIndex = len - 1;
    	if (nums[0] > nums[1])
    		return 0;
    		
    	if (nums[lastIndex] > nums[lastIndex - 1])
    		return lastIndex;
    	
    	int low  = 0;
    	int high = lastIndex;
    	
    	while (low < high) {
    		int middle = (low + high) / 2;
    		int middleNext = middle + 1;
    		if (nums[middle] > nums[middleNext]) {
    			// since nums[-1] is negative infinity and the slope
    			// here is decreasing, a peak must exist between low and middle
    			high = middle;
    		} else {
    			// since nums[len] is negative infinity and the slope
    			// here is increasing, a peak must exist between middle and high
    			low = middleNext;
    		}
    	}
    	
    	return -1;
    }
}
