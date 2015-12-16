package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        List<Character> sChars = new ArrayList<>();
        List<Character> tChars = new ArrayList<>();
        for (int i = 0; i < s.length(); ++i) {
            sChars.add(s.charAt(i));
            tChars.add(t.charAt(i));
        }
        Collections.sort(sChars);
        Collections.sort(tChars);
        
        return sChars.equals(tChars);
    }
}
