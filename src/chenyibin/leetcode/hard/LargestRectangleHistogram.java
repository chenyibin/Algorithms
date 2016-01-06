package chenyibin.leetcode.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Problem #84 on leetcode.com:
 * Given non-negative integers representing a histogram's bar height,
 * find the area of largest rectangle in the histogram.
 * The width of each bar is 1.
 * @author Yibin Chen
 */
public class LargestRectangleHistogram
{
    public int largestRectangleArea(int[] height)
    {
        int len = height.length;
        Deque<Integer> stack = new ArrayDeque<>(len);
        int largestArea = 0;
        
        for (int i = 0; i < len; ++i) {
            while (!stack.isEmpty() && height[i] < height[stack.peek()]) {
                int top = stack.pop();
                int popHeight = height[top];
                int width = (stack.isEmpty()) ? i : i - stack.peek() - 1;
                largestArea = Math.max(largestArea, popHeight * width);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int top = stack.pop();
            int popHeight = height[top];
            int width = (stack.isEmpty()) ? len : len - stack.peek() - 1;
            largestArea = Math.max(largestArea, popHeight * width);
        }
        return largestArea;
    }
}
