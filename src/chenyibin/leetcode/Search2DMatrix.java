package chenyibin.leetcode;

/**
 * Problem #74 on leetcode.com:
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * Integers in each row of the matrix are sorted from left to right and
 * the first integer of each row is greater than the last integer of the previous row.
 * @author Yibin Chen
 */
public class Search2DMatrix {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }
        int cols = matrix[0].length;
        if (cols == 0) {
            return false;
        }
        int lo = 0;
        int hi = rows - 1;
        while (lo < hi) {
            int mid = (hi + lo + 1) >>> 1;
            if (matrix[mid][0] == target) {
                return true;
            }
            if (matrix[mid][0] < target) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }
        
        int row = lo;
        
        lo = 0;
        hi = cols - 1;
        while (lo <= hi) {
            int mid = (hi + lo) >>> 1;
            if (matrix[row][mid] == target) {
                return true;
            }
            if (matrix[row][mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }
}
