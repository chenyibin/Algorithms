package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem #22 from leetcode.com:</br>
 * 
 * Given n pairs of parentheses, write a function to generate
 * all combinations of well-formed parentheses. 
 * 
 * @author Yibin Chen
 */
public class GenerateParentheses
{
    StringBuilder builder;
    List<String> results;
    
    public List<String> generateParenthesis(int n)
    {
        builder = new StringBuilder();
        results = new ArrayList<String>();
        recurse(n, 0);
        return results;
    }
    
    private void recurse(int openParenLeft, int closeParenLeft)
    {
        if (openParenLeft == 0 && closeParenLeft == 0) {
            results.add(builder.toString());
        }
        int origLen = builder.length();
        if (openParenLeft > 0) {
            builder.append('(');
            recurse(openParenLeft - 1, closeParenLeft + 1);
            builder.setLength(origLen);
        }
        
        if (closeParenLeft > 0) {
            builder.append(')');
            recurse(openParenLeft, closeParenLeft - 1);
            builder.setLength(origLen);
        }
    }
    
}
