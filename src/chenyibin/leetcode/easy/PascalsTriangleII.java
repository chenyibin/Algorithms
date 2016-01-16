package chenyibin.leetcode.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex)
    {
        if (rowIndex < 0) {
            return Collections.emptyList();
        }
        List<Integer> first = new ArrayList<>(1);
        first.add(1);
        List<Integer> lastRow = first;
        
        int numIters = rowIndex + 1;

        int prevI = 1;
        for (int i = 2; i <= numIters; ++i) {
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
        }
        return lastRow;
    }
}
