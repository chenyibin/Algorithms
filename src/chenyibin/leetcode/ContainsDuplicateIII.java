package chenyibin.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeSet;

public class ContainsDuplicateIII {
    
    TreeSet<NumIndex> window = new TreeSet<>();
    Deque<NumIndex> indexQ = new LinkedList<>();
    int sumDiff;
    int indexDiff;
    
    private static class NumIndex implements Comparable<NumIndex>
    {
        public int num;
        public int index;
        
        public NumIndex(int num, int index)
        {
            this.num = num;
            this.index = index;
        }
        
        @Override
        public int compareTo(NumIndex o) {
            NumIndex other = (NumIndex) o;
            
            if (this.num == other.num) {
                return Integer.compare(this.index, other.index);
            }
            return Integer.compare(this.num, other.num);
        }
    }
    
    boolean checkWindow(NumIndex check)
    {
        NumIndex down = window.lower(check);
        if (down != null)
        {
            long diff = (long)check.num - down.num;
            if (diff <= sumDiff)
                return true;
        }
        NumIndex up = window.higher(check);
        if (up != null)
        {
            long diff = (long)up.num - check.num;
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
            NumIndex newNum = new NumIndex(nums[i], i);
            this.window.add(newNum);
            if (this.indexQ.size() > this.indexDiff)
            {
                NumIndex oldNum = this.indexQ.poll();
                this.window.remove(oldNum);
            }
            this.indexQ.offer(newNum);
            if (checkWindow(newNum))
            {
                return true;
            }
        }
        return false;
    }
}
