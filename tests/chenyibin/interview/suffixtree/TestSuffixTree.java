package chenyibin.interview.suffixtree;

import org.junit.Assert;
import org.junit.Test;

public class TestSuffixTree
{
    @Test
    public void testFindSixByteSuffixTree()
    {
        SuffixTree testObj = new SuffixTree(6);
        testObj.appendByte((byte) 1);
        testObj.appendByte((byte) 2);
        testObj.appendByte((byte) 2);
        testObj.appendByte((byte) 1);
        testObj.appendByte((byte) 2);
        testObj.appendByte((byte) 3);
        
        byte[] search = new byte[] {2, 1, 2, 3};
        int[] found = testObj.findLongestMatch(search);
        int[] expected = new int[] {2,4};
        Assert.assertArrayEquals(expected, found);
        
        search = new byte[] {1, 2, 2, 1};
        found = testObj.findLongestMatch(search);
        expected = new int[] {0, 4};
        Assert.assertArrayEquals(expected, found);
        
        search = new byte[] {1};
        found = testObj.findLongestMatch(search);
        expected = new int[] {0, 1};
        Assert.assertArrayEquals(expected, found);
    }
    
    @Test
    public void testSevenBytesInSixByteSuffixTree()
    {
        SuffixTree testObj = new SuffixTree(6);
        testObj.appendByte((byte) 1);
        testObj.appendByte((byte) 2);
        testObj.appendByte((byte) 2);
        testObj.appendByte((byte) 1);
        testObj.appendByte((byte) 2);
        testObj.appendByte((byte) 3);
        testObj.appendByte((byte) 4);
        
        testObj.print(System.out);
    }
}
