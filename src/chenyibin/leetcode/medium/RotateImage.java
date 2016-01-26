package chenyibin.leetcode.medium;

/**
 * Problem #48 on leetcode.com:
 * Given an n x n 2D matrix, rotate the matrix by 90 degrees clockwise.
 * @author Yibin Chen
 */
public class RotateImage
{
    public void rotate(int[][] matrix)
    {
        int size = matrix.length;
        if (size == 0) {
            return;
        }
        int endIndex = size - 1;
        
        // Reverse each column
        for (int col = 0; col < size; ++col)
        {
            int start = 0;
            int end = endIndex;
            while (start < end) {
                int temp = matrix[start][col];
                matrix[start++][col] = matrix[end][col];
                matrix[end--][col] = temp;
            }
        }
        
        // Transpose
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < i; ++j)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
