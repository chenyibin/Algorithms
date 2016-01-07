package chenyibin.leetcode.easy;

/**
 * Problem #14 on leetcode.com:</br>
 * Write a function to find the longest common prefix string amongst an array of strings. 
 * @author Yibin Chen
 */
public class LongestCommonPrefix
{
    public String longestCommonPrefix(String[] strs)
    {
        if (strs.length == 0) {
            return "";
        }
        int prefixIndex = -1;
        boolean found = false;
        while (!found) {
            boolean first = true;
            char charAtFirst = 0;
            ++prefixIndex;
            for (int i = 0; i < strs.length; ++i) {
                String thisString = strs[i];
                if (prefixIndex >= thisString.length()) {
                    found = true;
                    break;
                }
                char currentChar = strs[i].charAt(prefixIndex);
                if (first) {
                    charAtFirst = currentChar;
                    first = false;
                } else if(charAtFirst != currentChar) {
                    found = true;
                    break;
                }
            }
        }
        if (prefixIndex == 0) {
            return "";
        }
        return strs[0].substring(0, prefixIndex);
    }
}
