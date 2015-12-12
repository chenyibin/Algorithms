package chenyibin.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem #239 on leetcode.com:
 * Given an array nums, there is a sliding window of size k moving from left to right of the array.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return a list containing the maximum number of each window and at each step.
 * 
 * @author Yibin Chen
 */
public class SlidingWindowMax
{

    public int[] maxSlidingWindow(int[] nums, int k)
    {
        int resultSize = nums.length - k + 1;
        int[] result = new int[resultSize];

        int offset = k - 1;

        // Use a double ended queue which maintains ordering
        // The top of the queue is always the largest element in the queue
        Deque<Integer> windowDeque = new LinkedList<Integer>();

        for (int i = 0; i < nums.length; ++i) {

            if (!windowDeque.isEmpty() && windowDeque.peek() <= i - k) {
                windowDeque.poll();
            }

            int currentNum = nums[i];

            while (!windowDeque.isEmpty() && nums[windowDeque.peekLast()] < currentNum) {
                windowDeque.pollLast();
            }
            
            windowDeque.offer(i);
            int resultIndex = i - offset;
            if (resultIndex  >= 0) {
                result[resultIndex] = nums[windowDeque.peek()];
            }
        }
        return result;
    }
}
