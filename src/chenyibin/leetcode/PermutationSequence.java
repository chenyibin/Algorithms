package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
    
    static int[] factorials = new int[10];
    static {
        factorials[0] = 1;
        for (int i = 1; i < 10; ++i) {
            factorials[i] = factorials[i-1] * i;
        }
    }
    
    public String getPermutation(int n, int k)
    {
        List<Character> numbersRemaining = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            numbersRemaining.add(Character.forDigit(i, 10));
        }
        
        // decrement k since permutations are counted starting from 0
        --k;
        
        StringBuilder result = new StringBuilder();
        for (int i = n - 1; i >= 0; --i)
        {
            int pick = k / factorials[i];
            k = k % factorials[i];
            result.append(numbersRemaining.get(pick));
            numbersRemaining.remove(pick);
        }
        return result.toString();
    }
    
    
}
