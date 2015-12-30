package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem #46 on leetcode.com:
 * Given a collection of distinct numbers, return all possible permutations.
 * 
 * @author Yibin Chen
 */
public class Permutations
{
    List<List<Integer>> result;
    int[] nums;
    
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        this.result = new ArrayList<>();

        if (nums.length == 0) {
            return result;
        }
        
        List<Integer> backtrack = new ArrayList<>();
        recurse(backtrack, nums.length);
        
        return result;
    }
    
    private void recurse(List<Integer> backtrack, int end) {
        if (end == 0) {
            List<Integer> copy = new ArrayList<>(backtrack);
            result.add(copy);
        }
        int endMinus = end - 1;
        int origSize = backtrack.size();
        for (int i = 0; i < end; ++i) {
            backtrack.add(this.nums[i]);
            // swap is used to effectively remove the used item from the set
            swap(i, endMinus);
            recurse(backtrack, endMinus);
            backtrack.remove(origSize);
            swap(i, endMinus);
        }
    }
    
    private void swap(int pos1, int pos2)
    {
        int temp = this.nums[pos1];
        this.nums[pos1] = this.nums[pos2];
        this.nums[pos2] = temp;
    }
    
}
