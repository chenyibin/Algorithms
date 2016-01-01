package chenyibin.leetcode;

/**
 * Problem #316 on leetcode.com:
 * Given a string which contains only lowercase letters, remove duplicate letters.
 * The result must be the smallest in lexicographical order among all possible results.
 * @author Yibin Chen
 */
public class RemoveDuplicateLetters
{
    public String removeDuplicateLetters(String s)
    {
        int[] counts = new int['z' + 1];
        boolean[] used = new boolean['z' + 1];
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            ++counts[c];
        }

        StringBuilder result = new StringBuilder(" ");
        int last = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            --counts[c];
            if (used[c]) {
                continue;
            }
            while (counts[result.charAt(last)] > 0 && result.charAt(last) > c) {
                used[result.charAt(last)] = false;
                result.setLength(last);
                --last;
            }
            result.append(c);
            used[c] = true;
            ++last;
        }
        
        return result.substring(1);
    }
}
