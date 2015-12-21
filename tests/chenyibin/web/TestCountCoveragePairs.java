package chenyibin.web;

import org.junit.Assert;
import org.junit.Test;

public class TestCountCoveragePairs
{
    @Test
    public void testSimpleThreeElementCase()
    {
        int[] numbers = {7,5,2};
        
        CountCoveragePairs solver = new CountCoveragePairs();
        int count = solver.countPairs(numbers);
        Assert.assertEquals(2, count);
    }
    
    @Test
    public void testThreeCoverageTrees()
    {
        int[] numbers = {
            Integer.valueOf("111000", 2),
            Integer.valueOf("010000", 2),
            Integer.valueOf("101000", 2),
            Integer.valueOf("001000", 2),
            Integer.valueOf("000111", 2),
            Integer.valueOf("000110", 2),
            Integer.valueOf("000101", 2),
            Integer.valueOf("000100", 2)};
            
        CountCoveragePairs solver = new CountCoveragePairs();
        int count = solver.countPairs(numbers);
        Assert.assertEquals(3 + 1 + 3 + 1 + 1, count);
    }
    
     
    @Test
    public void testDeepConvergences()
    {
        int[] numbers = {
            Integer.valueOf("11111", 2),
            Integer.valueOf("11110", 2),
            Integer.valueOf("01111", 2),
            Integer.valueOf("01110", 2),
            Integer.valueOf("00110", 2),
            Integer.valueOf("01100", 2),
            Integer.valueOf("11101", 2),
            Integer.valueOf("00100", 2)};

            
        CountCoveragePairs solver = new CountCoveragePairs();
        int count = solver.countPairs(numbers);
        Assert.assertEquals(7 + 4 + 4 + 2 + 3 + 1 + 1, count);
    }
}
