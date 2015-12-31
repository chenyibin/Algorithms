package chenyibin.leetcode;

/**
 * Problem #221 on leetcode.com:
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest square containing all 1's and return its area.
 * @author Yibin Chen
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return 0;
        }
        
        int maxSideLength = 0;
        
        int[][] squareLengths = new int[rows][cols];
        
        // Initialize the sides
        for (int row = 0; row < rows; ++row) {
            if (matrix[row][0] == '1') {
                squareLengths[row][0] = 1;
                maxSideLength = 1;
            }
        }
        
        for (int col = 0; col < cols; ++col) {
            if (matrix[0][col] == '1') {
                squareLengths[0][col] = 1;
                maxSideLength = 1;
            }
        }
 
        // Fill the DP table
        for (int row = 1; row < rows; ++row)
        {
            for (int col = 1; col < cols; ++col) {
                if (matrix[row][col] != '1') {
                    continue;
                }
                int extension = Math.min(Math.min(
                    squareLengths[row-1][col-1],
                    squareLengths[row][col-1]),
                    squareLengths[row-1][col]);
                int sideLength = extension + 1;
                squareLengths[row][col] = sideLength;
                maxSideLength = Math.max(sideLength, maxSideLength);
            }
        }
        
        return maxSideLength * maxSideLength;
    }
}
