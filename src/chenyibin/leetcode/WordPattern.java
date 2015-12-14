package chenyibin.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem #290 on leetcode.com:</br>
 * 
 * Given a pattern and a string str, find if str follows the same pattern.
 * Assume pattern contains only lowercase letters, and str contains lowercase
 * letters separated by a single space. 
 * @author Yibin Chen
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String str)
    {
        String[] split = str.split(" ");
        
        if (split.length != pattern.length()) {
            return false;
        }
        
        Map<Character,String> charMap = new HashMap<>();
        Set<String> usedStrings = new HashSet<>();
        
        for (int i = 0; i < split.length; ++i) {
            Character curChar = pattern.charAt(i);
            String mappedString = charMap.get(curChar);
            String currentString = split[i];
            if (mappedString == null) {
                if (usedStrings.contains(currentString)) {
                    return false;
                }
                usedStrings.add(currentString);
                charMap.put(curChar, currentString);
            } else if (!mappedString.equals(currentString)) {
                return false;
            }
        }
        
        return true;
    }
}
