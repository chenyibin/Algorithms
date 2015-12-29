package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #39 on leetcode.com:
 * Given a set of candidate numbers and a target number,
 * find all unique combinations where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times. 
 * @author Yibin Chen
 */
public class CombinationSum {
    
    int[] candidates;
    List<List<Integer>> result;
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        this.result = new ArrayList<>();
        
        List<Integer> current = new ArrayList<>();
        findCandidates(current, 0, target);
        
        return this.result;
    }

    private void findCandidates(List<Integer> current, int lastIndex, int target)
    {
        if (target == 0) {
            List<Integer> newResult = new ArrayList<>(current);
            result.add(newResult);
        }
        
        int origSize = current.size();
        for (int i = lastIndex; i < candidates.length && candidates[i] <= target; ++i) {
            current.add(candidates[i]);
            findCandidates(current, i, target - candidates[i]);
            current.remove(origSize);
        }
    }
}
