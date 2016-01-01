package chenyibin.leetcode;

public class Search2DMatrixII
{
    public boolean searchMatrix(int[][] matrix, int target) {
        
        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return false;
        }
        
        int row = 0;
        int col = cols - 1;
        while (row < rows && col >= 0) {
            int value = matrix[row][col];
            if (target == value) {
                return true;
            } else if (target > value) {
                ++row;
            } else {
                --col;
            }
        }
        return false;
    }
}
