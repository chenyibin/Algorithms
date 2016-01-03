package chenyibin.leetcode;

/**
 * Given a m x n grid filled with non-negative numbers,
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * @author Yibin Chen
 */
public class MinimumPathSum
{
    public int minPathSum(int[][] grid)
    {
        int rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        if (cols == 0) {
            return 0;
        }
        
        int[] pathCost = new int[cols];
        pathCost[0] = grid[0][0];
        int prev = pathCost[0];
        for (int col = 1; col < cols; ++col) {
            pathCost[col] = prev + grid[0][col];
            prev = pathCost[col];
        }
        
        for (int row = 1; row < rows; ++row) {
            pathCost[0] += grid[row][0];
            prev = pathCost[0];
            for (int col = 1; col < cols; ++col) {
                pathCost[col] = Math.min(pathCost[col], prev) + grid[row][col];
                prev = pathCost[col];
            }
        }
        return prev;
    }
}
