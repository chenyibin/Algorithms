package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums)
    {
        List<List<Integer>> sequenceTable = new ArrayList<>();
        for (int num : nums) {
            if (sequenceTable.isEmpty()) {
                sequenceTable.add(new LinkedList<>());
                sequenceTable.get(0).add(num);
                continue;
            }
            
            boolean isSmallest = true;
            for (int i = sequenceTable.size() - 1; i >= 0; --i) {
                List<Integer> sequence = sequenceTable.get(i);
                if (sequence.get(0) < num) {
                    isSmallest = false;
                    List<Integer> copy = new LinkedList<>();
                    copy.add(num);
                    copy.addAll(sequence);
                    
                    int plus = i + 1;
                    if (plus == sequenceTable.size()) {
                        sequenceTable.add(copy);
                    } else {
                        sequenceTable.set(plus, copy);
                    }
                }
            }
            
            if (isSmallest) {
                sequenceTable.get(0).set(0, num);
            }

        }
        
        List<Integer> longestSeq = sequenceTable.get(sequenceTable.size() - 1);
        return longestSeq.size();
    }
}
