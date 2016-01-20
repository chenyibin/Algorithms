package chenyibin.leetcode.medium;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Problem #187 on leetcode.com:
 * DNA is composed of a series of nucleotides abbreviated as A, C, G, and T.
 * Write a function to find all the 10-letter-long substrings 
 * that occur more than once in a DNA molecule.
 * @author Yibin Chen
 */
public class RepeatedDnaSequences
{
    static final int MASK = 0x0FFFFF;
    
    public List<String> findRepeatedDnaSequences(String s)
    {
        if (s.length() <= 10) {
            return Collections.emptyList();
        }
        List<String> result = new LinkedList<>();
        // We could use a hash here but there are only 2^20 possible
        // sequences of A, C, G, and T of length 10.
        // So its possible to give every possibility a boolean.
        boolean[] words = new boolean[1048576]; // 2^20
        
        // Doubles array stores items that we've already added to our solution
        boolean[] doubles = new boolean[1048576]; // 2^20
        int i = 0;
        
        // sequence encodes string of length 10 directly as integer
        int sequence = 0;
        for (; i < 10; ++i) {
            sequence <<= 2;
            sequence |= getCode(s.charAt(i));
        }
        words[sequence] = true;
        for (; i < s.length(); ++i) {
            char c = s.charAt(i);
            sequence <<= 2;
            sequence |= getCode(c);
            sequence &= MASK;
            if (words[sequence]) {
                if (doubles[sequence]) {
                    continue;
                }
                // Need to +1 since substring second argument is exclusive
                result.add(s.substring(i-9, i+1));
                doubles[sequence]= true;
            } else {
                words[sequence] = true;
            }
        }
        
        return result;
    }
    
    static int getCode(char c) {
        if (c == 'C') {
            return 1;
        } else if (c == 'G') {
            return 2;
        } else if (c == 'T') {
            return 3;
        }
        return 0;
    }
    
}
