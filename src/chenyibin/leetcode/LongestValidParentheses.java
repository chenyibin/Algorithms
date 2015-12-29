package chenyibin.leetcode;

/**
 * Problem #32 on leetcode.com:
 * Given a string containing just the characters '(' and ')',
 * find the length of the longest valid (well-formed) parentheses substring.
 * 
 * @author Yibin Chen
 */
public class LongestValidParentheses
{
    public int longestValidParentheses(String s)
    {
        int[] longestValid = new int[s.length()];
        
        int longest = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == ')') {
                // Index at which the opening parenthesis for
                // this closing parenthesis must be
                int openParenthesis = i - longestValid[i-1] - 1;
                if (openParenthesis < 0) {
                    continue;
                }
                if (s.charAt(openParenthesis) == '(') {
                    // We caught a valid one
                    int longestTillHere = 2 + longestValid[i-1];
                    if (openParenthesis > 0) {
                        longestTillHere += longestValid[openParenthesis-1];
                    }
                    longestValid[i] = longestTillHere;
                    longest = Math.max(longest, longestTillHere);
                }
            }
        }
        return longest;
    } 
}
