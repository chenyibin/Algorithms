package chenyibin.leetcode;

/**
 * Problem #73 on leetcode.com:
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0.
 * Do it in place with O(1) space.
 * @author Yibin Chen
 */
public class SetMatrixZeroes
{
    public void setZeroes(int[][] matrix)
    {
        int rows = matrix.length;
        if (rows == 0) {
            return;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return;
        }

        boolean zeroFirstRow = false;
        for (int row = 0; row < rows; ++row) {
            if (matrix[row][0] == 0) {
                zeroFirstRow = true;
                break;
            }
        }
        boolean zeroFirstCol = false;
        for (int col = 0; col < cols; ++col) {
            if (matrix[0][col] == 0) {
                zeroFirstCol = true;
                break;
            }
        }
            
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (matrix[row][col] == 0) {
                    matrix[row][0] = 0;
                    matrix[0][col] = 0;
                }
            }
        }
        
        for (int row = 1; row < rows; ++row) {
            for (int col = 1; col < cols; ++col) {
                if (matrix[row][0] == 0 || matrix[0][col] == 0) {
                    matrix[row][col] = 0;
                }
            }
        }
        
        if (zeroFirstRow) {
            for (int row = 0; row < rows; ++row) {
                matrix[row][0] = 0;
            }
        }
        if (zeroFirstCol) {
            for (int col = 0; col < cols; ++col) {
                matrix[0][col] = 0;
            }
        }
    }
}
