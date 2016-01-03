package chenyibin.leetcode;


public class UniquePathsII
{
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        if (rows == 0) {
            return 0;
        }
        int cols = obstacleGrid[0].length;
        if (cols == 0) {
            return 0;
        }
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        
        int[] pathsToRow = new int[cols];
        int prev = 1;
        pathsToRow[0] = prev;
        for (int col = 1; col < cols; ++col) {
            if (obstacleGrid[0][col] == 1) {
                prev = 0;
            } 
            pathsToRow[col] = prev;
        }
        
        for (int row = 1; row < rows; ++row) {
            if (obstacleGrid[row][0] == 1) {
                pathsToRow[0] = 0;
            } 
            prev = pathsToRow[0];
            for (int col = 1; col < cols; ++col) {
                if (obstacleGrid[row][col] == 1) {
                    pathsToRow[col] = 0;
                } else {
                    pathsToRow[col] += prev;
                }
                prev = pathsToRow[col];
            }
        }
        return prev; 
    }
}
