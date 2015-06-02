package chenyibin.leetcode;

import org.junit.Test;

public class TestContainsDuplicateIII
{
    @Test
    public void testSimpleCase()
    {
        ContainsDuplicateIII solver = new ContainsDuplicateIII();
        int[] nums = {-1, 2147483647};
        solver.containsNearbyAlmostDuplicate(nums, 1, 2147483647);
    }
}
