package chenyibin.leetcode;

import java.util.Arrays;

public class UniquePaths {
    public int uniquePaths(int m, int n)
    {
        if (m == 0 || n == 0) {
            return 0;
        }
        
        int[] pathsToRow = new int[n];
        Arrays.fill(pathsToRow, 1);
        for (int row = 1; row < m; ++row) {
            int prev = 1;
            for (int col = 1; col < n; ++col) {
                pathsToRow[col] = prev + pathsToRow[col];
                prev = pathsToRow[col];
            }
        }
        return pathsToRow[n-1];
    }
}
