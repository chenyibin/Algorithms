package chenyibin.leetcode;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class TestProductOfArrayExceptSelf
{
    
    @Test
    public void testThreeElementsWithZeroes() {
        ProductOfArrayExceptSelf test = new ProductOfArrayExceptSelf();
        int[] testCase = {0,4,0};

        int[] output = test.productExceptSelf(testCase);
        
        List<Integer> result = Ints.asList(output);
        List<Integer> expected = Lists.newArrayList(0,0,0);
        Assert.assertEquals(expected, result);
    }
    
}
