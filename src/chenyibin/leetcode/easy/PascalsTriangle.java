package chenyibin.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PascalsTriangle
{
    public List<List<Integer>> generate(int numRows)
    {
        if (numRows == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> answer = new ArrayList<>(numRows);
        List<Integer> first = new ArrayList<>(1);
        first.add(1);
        answer.add(first);
        
        List<Integer> lastRow = first;
        int prevI = 1;
        for (int i = 2; i <= numRows; ++i) {
            List<Integer> newRow = new ArrayList<>(i);
            newRow.add(1);
            int prevJ = 0;
            for (int j = 1; j < prevI; ++j) {
                newRow.add(lastRow.get(prevJ) + lastRow.get(j));
                prevJ = j;
            }
            newRow.add(1);
            prevI = i;
            lastRow = newRow;
            answer.add(newRow);
        }
        return answer;
    }
}
