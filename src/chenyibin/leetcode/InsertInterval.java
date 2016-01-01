package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import chenyibin.leetcode.common.Interval;

/**
 * Problem #57 on leetcode.com:
 * Given a set of sorted non-overlapping intervals,
 * insert a new interval merging if necessary. 
 * @author Yibin Chen
 */
public class InsertInterval
{
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.isEmpty()) {
            intervals.add(newInterval);
            return intervals;
        }
        
        List<Interval> result = new ArrayList<>(intervals.size());
        
        Iterator<Interval> iter = intervals.iterator();
        Interval current = iter.next();
        while (current != null && newInterval.start > current.end)
        {
            result.add(current);
            if (iter.hasNext()) {
                current = iter.next();
            } else {
                current = null;
            }
        }
        
        while (current != null && newInterval.end >= current.start) {
            newInterval.start = Math.min(newInterval.start, current.start);
            newInterval.end = Math.max(newInterval.end, current.end);
            if (iter.hasNext()) {
                current = iter.next();
            } else {
                current = null;
            }
        }
        
        result.add(newInterval);
        if (current != null) {
            result.add(current);
        }
        while (iter.hasNext()) {
            result.add(iter.next());
        }
       
        return result;
    }
}
