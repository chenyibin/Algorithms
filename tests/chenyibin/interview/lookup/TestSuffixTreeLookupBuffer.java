package chenyibin.interview.lookup;

import org.junit.Assert;
import org.junit.Test;

import chenyibin.interview.lookup.SuffixTreeLookupBuffer;

public class TestSuffixTreeLookupBuffer
{
    SuffixTreeLookupBuffer testObj;
    
    @Test
    public void testFindInSixByteSuffixTree()
    {
        testObj = new SuffixTreeLookupBuffer(6);
        byte[] testBytes = new byte[] {1,2,2,1,2,3};
        appendBytesToTestObj(testBytes);
        
        byte[] search = new byte[] {2,1,2,3};
        int[] expected = new int[] {4,4};
        int[] found = testObj.findLongestMatch(search);
        Assert.assertArrayEquals(expected, found);
        
        search = new byte[] {1,2,2,1};
        expected = new int[] {6,4};
        found = testObj.findLongestMatch(search);
        Assert.assertArrayEquals(expected, found);
        
        search = new byte[] {1};
        expected = new int[] {6,1};
        found = testObj.findLongestMatch(search);
        Assert.assertArrayEquals(expected, found);
    }
    
    @Test
    public void testTenBytesInEightByteSuffixTree()
    {
        testObj = new SuffixTreeLookupBuffer(8);
        byte[] testBytes = new byte[] {1,2,2,1,2,3,1,2,4,4};
        appendBytesToTestObj(testBytes);

        byte[] search = new byte[] {4,4,4};
        int[] expected = new int[] {2,2};
        int[] found = testObj.findLongestMatch(search);
        Assert.assertArrayEquals(expected, found);
        
        search = new byte[] {2,1,2,3,1,2,4,4};
        expected = new int[] {8,8};
        found = testObj.findLongestMatch(search);
        Assert.assertArrayEquals(expected, found);
    }
    
    @Test
    public void testDeleteWhileActivePointOnActiveInternalLeafPath()
    {
        testObj = new SuffixTreeLookupBuffer(4);
        byte[] testBytes = new byte[] {1,1,1,1,2,1,1};
        appendBytesToTestObj(testBytes);

        byte[] search = new byte[] {1,1};
        int[] expected = new int[] {2,2};
        int[] found = testObj.findLongestMatch(search);
        Assert.assertArrayEquals(expected, found);
        
        search = new byte[] {2,1,2};
        expected = new int[] {3,2};
        found = testObj.findLongestMatch(search);
        Assert.assertArrayEquals(expected, found);
    }
    
    @Test
    public void testDeleteWhileActivePointOnActiveRootLeafPath()
    {
        testObj = new SuffixTreeLookupBuffer(4);
        byte[] testBytes = new byte[] {1,1,1,1,2,1};
        appendBytesToTestObj(testBytes);

        byte[] search = new byte[] {1,1,2,1};
        int[] expected = new int[] {4,4};
        int[] found = testObj.findLongestMatch(search);
        Assert.assertArrayEquals(expected, found);
        
        search = new byte[] {2,1};
        expected = new int[] {2,2};
        found = testObj.findLongestMatch(search);
        Assert.assertArrayEquals(expected, found);
    }
    
    @Test
    public void testFullBufferReplaceTwoTimes()
    {
        testObj = new SuffixTreeLookupBuffer(4);
        byte[] testBytes = new byte[] {1,1,1,1,2,2,2,3,4,4,4,4};
        appendBytesToTestObj(testBytes);
        
        byte[] search = new byte[] {4,4,4,4};
        int[] expected = new int[] {4,4};
        int[] found = testObj.findLongestMatch(search);
        Assert.assertArrayEquals(expected, found);
        
        search = new byte[] {3,4};
        found = testObj.findLongestMatch(search);
        Assert.assertNull(found);

        search = new byte[] {2,3};
        found = testObj.findLongestMatch(search);
        Assert.assertNull(found);

        search = new byte[] {1,1};
        found = testObj.findLongestMatch(search);
        Assert.assertNull(found);
    }
    
    public void appendBytesToTestObj(byte[] testBytes)
    {
        for (byte oneByte : testBytes) {
            testObj.appendByte(oneByte);
        }
    }
}
