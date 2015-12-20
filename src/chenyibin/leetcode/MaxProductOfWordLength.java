package chenyibin.leetcode;

import java.util.Arrays;

/**
 * Problem #318 on leetcode.com:
 * Given a string array, find the maximum value of length(word[i]) * length(word[j])
 * where the two words do not share common letters. Each word will contain only lower case letters.
 * If no such two words exist, return 0.
 * 
 * @author Yibin Chen
 */
public class MaxProductOfWordLength
{
    static int[] charBit = new int[26 + 'a'];
    
    static {
        int offset = 0;
        for (int i = 'a'; i < charBit.length; ++i) {
            charBit[i] = 1 << offset++;
        }
    }
    
    public int maxProduct(String[] words) {
        
        Arrays.sort(words, (o1, o2) ->
            Integer.compare(((String) o2).length(), ((String) o1).length())
        );
        
        int charMasks[] = new int[words.length];
        
        for (int i = 0; i < words.length; ++i) {
            charMasks[i] = createMask(words[i]);
        }
        
        int maxProduct = 0;
        for (int i = 0; i < words.length; ++i)
        {
            if (words[i].length() * words[i].length() < maxProduct) {
                break;
            }
            for (int j = i; j < words.length; ++j)
            {
                
                if ((charMasks[i] & charMasks[j]) != 0) {
                    // The two words have overlapping characters
                    continue;
                }
                
                maxProduct = Math.max(words[i].length() * words[j].length(), maxProduct);
                break;
            }
        }
        
        return maxProduct;
        
    }

    private int createMask(String word)
    {
        int mask = 0;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            mask |= charBit[c];
        }
        return mask;
    }
}
