package chenyibin.leetcode;

import java.util.Arrays;

/**
 * Problem #85 on leetcode.com:
 * Given a 2D binary matrix filled with 0's and 1's,
 * find the largest rectangle containing all ones and return its area.
 * @author Yibin Chen
 */
public class MaximalRectangle
{
    public int maximalRectangle(char[][] matrix)
    {
        int numRows = matrix.length;
        if (numRows == 0) {
            return 0;
        }
        int numCols = matrix[0].length;
        if (numCols == 0) {
            return 0;
        }
        
        int maxArea = 0;
        int[] heights = new int[numCols];
        int[] leftBoundaries = new int[numCols];
        int[] rightBoundaries = new int[numCols];

        Arrays.fill(leftBoundaries, Integer.MIN_VALUE);
        Arrays.fill(rightBoundaries, Integer.MAX_VALUE);
        
        for (int row = 0; row < numRows; ++row) {
            
            int currentLeftBound = Integer.MIN_VALUE;
            // For each column we calculate the area of the rectangle
            // starting at the current row, whose height is maximized.
            
            // Perform pass sweep accross columns:
            // 1. From left to right to figure out rectangle height and
            //    boundary of the rectangle on the left side.
            for (int col = 0; col < numCols; ++col) {
                if (matrix[row][col] == '1') {
                    heights[col] = heights[col] + 1;
                    if (currentLeftBound == Integer.MIN_VALUE) {
                        currentLeftBound = col;
                    }
                    // Pick the left bound that is right-most
                    leftBoundaries[col] = Math.max(currentLeftBound, leftBoundaries[col]);
                } else {
                    heights[col] = 0;
                    currentLeftBound = Integer.MIN_VALUE;
                    leftBoundaries[col] = Integer.MIN_VALUE;
                }
            }
            
            // 2. From right to left to figure out the boundary
            //    of the rectangle on the right side and then
            //    calculate the area of the rectangle.
            int currentRightBound = Integer.MAX_VALUE;
            for (int col = numCols - 1; col >= 0; --col) {
                if (matrix[row][col] == '1') {
                    if (currentRightBound == Integer.MAX_VALUE) {
                        currentRightBound = col;
                    }
                    // Pick the right bound that is left-most
                    rightBoundaries[col] = Math.min(currentRightBound, rightBoundaries[col]);
                    
                    // Calculate rectangle area
                    int rectangleArea = (rightBoundaries[col] - leftBoundaries[col] + 1) * heights[col];
                    maxArea = Math.max(maxArea, rectangleArea);
                } else {
                    currentRightBound = Integer.MAX_VALUE;
                    rightBoundaries[col] = Integer.MAX_VALUE;
                }
            }
        }
        
        return maxArea;
    }
}
