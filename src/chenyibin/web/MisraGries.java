package chenyibin.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * The Misra-Gries algorithm finds the elements in an array whose
 * frequency is greater than numElements / numFrequents if such numbers exist.
 * 
 * @author Yibin Chen
 */
public class MisraGries
{
    private int numFrequents;
    private Map<Integer, Integer> counters;

    public MisraGries(int numFrequents)
    {
        this.numFrequents = numFrequents;
        this.counters = new HashMap<>();
    }
    
    public List<Integer> getFrequents(int[] nums)
    {
        // Find all the high frequency candidates
        for (int num : nums)
        {
            Integer count = counters.get(num);
            if (count != null) {
                // If it's one of the counters, increment
                counters.put(num, ++count);
            } else if (counters.size()  < numFrequents) {
                // If the counters aren't full add this one
                counters.put(num, 1);
            } else {
                // If no match decrement every counter and
                // remove the ones that would be reset to 0
                // Using an iterator here so we can invoke the remove() method
                Iterator<Entry<Integer,Integer>> iter = counters.entrySet().iterator();
                while (iter.hasNext())
                {
                    Entry<Integer, Integer> entry = iter.next();
                    int entryValue = entry.getValue();
                    if (entryValue == 1) {
                        iter.remove();
                    } else {
                        // calling setValue() will update the original Map
                        entry.setValue(entryValue - 1);
                    }
                }
            }
        }
        
        // Reset the counters for second pass
        for (Entry<Integer,Integer> entry : counters.entrySet()) {
            entry.setValue(0);
        }
        
        // Second pass finds the actual frequency of each candidate
        for (int num : nums) {
            Integer count = counters.get(num);
            if (count != null) {
                counters.put(num, ++count);
            }
        }
        
        int threshold = nums.length / numFrequents;
        return counters.entrySet().stream()
            .filter((entry) -> entry.getValue() > threshold)
            .map((entry) -> entry.getKey())
            .collect(Collectors.toList());
    }
}
