package chenyibin.leetcode.medium;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Problem #120 on leetcode.com:
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may either move down or down-right.
 * @author Yibin Chen
 */
public class Triangle
{
    public int minimumTotal(List<List<Integer>> triangle)
    {
        int[] mem = new int[triangle.size()];
        Arrays.fill(mem, Integer.MAX_VALUE);
        Iterator<List<Integer>> iter = triangle.iterator();
        if (iter.hasNext()) {
            List<Integer> first = iter.next();
            mem[0] = first.get(0);
        }
        
        while (iter.hasNext())
        {
            List<Integer> level = iter.next();
            int prevMin = mem[0];
            mem[0] += level.get(0);
            for (int i = 1; i < level.size(); ++i)
            {
                int nextPrev = mem[i];
                mem[i] = Math.min(mem[i], prevMin) + level.get(i);
                prevMin = nextPrev;
            }
        }

        int minTotal = Integer.MAX_VALUE;
        for (int val : mem) {
            minTotal = Math.min(minTotal, val);
        }
        return minTotal;
    }
}
