package chenyibin.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Problem #282 from leetcode.com:</br>
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators +, -, or * between
 * the digits so they evaluate to the target value. 
 * 
 * @author Yibin Chen
 */
public class ExpressionAddOperators
{
    String num;
    List<String> solutions;
    int target;
    
    public List<String> addOperators(String num, int target)
    {
        if (num == null | num.isEmpty()) {
            return Collections.emptyList();
        }
        this.num = num;
        this.target = target;
        
        this.solutions = new LinkedList<>();
        StringBuilder resultBuilder = new StringBuilder();
        
        solve(resultBuilder, 0, 0, 0);
        return this.solutions;
    }
    
    public void solve(StringBuilder partialResult, int position,
        long result, long multLookback)
    {
        if (position == num.length())
        {
            if (result == target) {
                solutions.add(partialResult.toString());
            }
            return;
        }
        
        int origLen = partialResult.length();
        int posPlus = position + 1;
        for (int i = posPlus; i <= num.length(); ++i)
        {
            if (num.charAt(position) == '0' && i != posPlus)
            {
                // avoid invalid numbers such as 05
                break;
            }
            
            String curNumStr = this.num.substring(position, i);
            long curNum = Long.parseLong(curNumStr);
            if (position == 0) {
                partialResult.append(curNumStr);
                solve(partialResult, i, curNum, curNum);
                partialResult.setLength(origLen);
            } else {
                partialResult.append("+" + curNumStr);
                solve(partialResult, i, result + curNum, curNum);
                partialResult.setLength(origLen);

                partialResult.append("-" + curNumStr);
                solve(partialResult, i, result - curNum, -curNum);
                partialResult.setLength(origLen);

                partialResult.append("*" + curNumStr);
                long multWithPrev = multLookback * curNum;
                solve(partialResult, i, result - multLookback + multWithPrev, multWithPrev);
                partialResult.setLength(origLen);
            }
        }
        
    }
}
