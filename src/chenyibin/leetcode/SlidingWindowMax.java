package chenyibin.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMax
{

    public int[] maxSlidingWindow(int[] nums, int k)
    {
        int resultSize = nums.length - k + 1;
        int[] result = new int[resultSize];

        int offset = k - 1;

        Deque<Integer> windowQueue = new LinkedList<Integer>();

        for (int i = 0; i < nums.length; ++i) {

            if (!windowQueue.isEmpty() && windowQueue.peek() <= i - k) {
                windowQueue.poll();
            }

            int currentNum = nums[i];

            while (!windowQueue.isEmpty() && nums[windowQueue.peekLast()] < currentNum) {
                windowQueue.pollLast();
            }
            
            windowQueue.offer(i);
            int resultIndex = i - offset;
            if (resultIndex  >= 0) {
                result[resultIndex] = nums[windowQueue.peek()];
            }
        }
        return result;
    }
}
