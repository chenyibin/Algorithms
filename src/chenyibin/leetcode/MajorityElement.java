package chenyibin.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem #169 on leetcode.com
 */
public class MajorityElement {
	
    Map<Integer,Integer> candidateCounts = new HashMap<Integer,Integer>(256);	

    public int majorityElement(int[] nums) {
    	int candidateFrequency = 0;
    	int majorityCandidate = 0;
    	int limit = nums.length / 2;
    	
    	for(int i : nums) {
    		Integer counter = candidateCounts.get(i);
    		if (counter == null) {
    			counter = 1;
    			candidateCounts.put(i, counter);
    		} else {
    			counter += 1;
    			candidateCounts.put(i, counter);
    		}
    		
    		if (counter > candidateFrequency) {
    			candidateFrequency = counter;
    			majorityCandidate = i;
    		}
    		if (candidateFrequency > limit)
    			break;
    	}
        
    	return majorityCandidate;
    }
}
