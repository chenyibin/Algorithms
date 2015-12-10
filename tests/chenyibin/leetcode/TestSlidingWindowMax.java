package chenyibin.leetcode;

import org.junit.Test;

import chenyibin.leetcode.common.PrintUtils;

public class TestSlidingWindowMax {

    @Test
    public void testExampleWindow()
    {
        int[] nums = {1,3,-1,-3,5,3,6,7};

        SlidingWindowMax solver = new SlidingWindowMax();
        int[] result = solver.maxSlidingWindow(nums, 3);
        
        System.out.println(PrintUtils.intArrayToString(result));
    }
}
