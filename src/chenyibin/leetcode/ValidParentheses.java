package chenyibin.leetcode;

import java.util.Stack;

/**
 * Problem #20 on leetcode.com:
 * 
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 * The brackets must close in the correct order,
 * "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 * 
 * @author Yibin Chen
 */
public class ValidParentheses
{
    public boolean isValid(String s)
    {
        Stack<Character> cs = new Stack<Character>();
        char[] schars = s.toCharArray();
        
        for (char c : schars) {
            if (c == '(' || c == '{' || c == '[') {
                cs.push(c);
            }
            else if
             (  (c == ')' && !cs.isEmpty() && cs.peek() == '(')
             || (c == ']' && !cs.isEmpty() && cs.peek() == '[')
             || (c == '}' && !cs.isEmpty() && cs.peek() == '{'))
            {
                cs.pop();
            } else {
                return false;
            }
        }
        return cs.isEmpty();
    }
}
