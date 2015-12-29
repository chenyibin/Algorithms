package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem #40 on leetcode.com:
 * Given a set of candidate numbers and a target number,
 * find all unique combinations where the candidate numbers sums to T.
 * Each candidate may only be used once in the combination. 
 * 
 * @author Yibin Chen
 */
public class CombinationSumII {
    
    int[] candidates;
    List<List<Integer>> result;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        this.result = new ArrayList<>();
        
        List<Integer> current = new ArrayList<>();
        findCandidates(current, -1, target);
        
        return this.result;
    }

    private void findCandidates(List<Integer> current, int lastIndex, int target)
    {
        if (target == 0) {
            List<Integer> newResult = new ArrayList<>(current);
            result.add(newResult);
        }
        
        int origSize = current.size();
        int prevCandidate = Integer.MIN_VALUE;
        for (int i = lastIndex + 1; i < candidates.length && candidates[i] <= target; ++i) {
            int currentCandidate = candidates[i];
            if (currentCandidate == prevCandidate) {
                continue;
            }
            current.add(candidates[i]);
            findCandidates(current, i, target - candidates[i]);
            current.remove(origSize);
            prevCandidate = currentCandidate;
        }
    }
}
