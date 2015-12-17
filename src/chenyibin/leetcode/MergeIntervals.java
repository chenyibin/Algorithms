package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import chenyibin.leetcode.common.Interval;

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals)
    {
        List<Interval> merged = new ArrayList<>();
        if (intervals.isEmpty()) {
            return merged;
        }
        
        Collections.sort(intervals, (v1, v2) -> Integer.compare(v1.start, v2.start));
        
        Iterator<Interval> iter = intervals.iterator();
        Interval merging = iter.next();
        while (iter.hasNext())
        {
            Interval val = iter.next();
            if (merging == null) {
                merging = val;
            }
            if (merging.end >= val.start) {
                merging.end = Math.max(merging.end, val.end);
            } else {
                merged.add(merging);
                merging = val;
            }
        }
        merged.add(merging);
        return merged;
    }
}
