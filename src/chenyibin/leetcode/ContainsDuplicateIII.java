package chenyibin.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * Problem #220 from leetcode.com:
 * Given an array of integers, find out whether there are two distinct indices i and j
 * such that the difference between nums[i] and nums[j] is at most t
 * and the difference between i and j is at most k. 
 * 
 * @author Yibin Chen
 */
public class ContainsDuplicateIII
{
    // The window contains a tree set of
    // the last k elements
    TreeSet<Integer> window = new TreeSet<>();
    
    // The index queue enforces that the window
    // only contains the last k elements
    Deque<Integer> indexQ = new LinkedList<>();
    int sumDiff;
    int indexDiff;
    
    boolean checkWindowHasConstraintSatisfyingEntry(Integer check)
    {
        Integer down = window.floor(check);
        if (down != null)
        {
            long diff = (long)check  - down;
            if (diff <= sumDiff)
                return true;
        }
        Integer up = window.ceiling(check);
        if (up != null)
        {
            long diff = (long)up - check;
            if (diff <= sumDiff)
                return true;
        }
        return false;
    }
    
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        this.sumDiff = t;
        this.indexDiff = k;
        
        for (int i = 0; i < nums.length; ++i)
        {
            Integer newNum = nums[i];
            if (this.indexQ.size() > this.indexDiff)
            {
                Integer oldNum = this.indexQ.poll();
                this.window.remove(oldNum);
            }
            this.indexQ.offer(newNum);
            if (checkWindowHasConstraintSatisfyingEntry(newNum))
            {
                return true;
            }
            this.window.add(newNum);
        }
        return false;
    }
}
