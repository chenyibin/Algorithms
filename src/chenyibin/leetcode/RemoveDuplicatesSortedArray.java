package chenyibin.leetcode;

public class RemoveDuplicatesSortedArray
{
    public int removeDuplicates(int[] nums)
    {
    	if (nums.length == 0)
    		return 0;
    		
        int trailer = 0;
        int header  = 1;
        
        int prev = nums[trailer];
        
        while (header < nums.length)
        {
        	if (prev != nums[header]) {
        		++trailer;
        		nums[trailer] = nums[header];
        		prev = nums[header];
        	}
        	++header;
        }
        return trailer + 1;
    }
}
