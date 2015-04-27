package chenyibin.leetcode;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import chenyibin.leetcode.common.ListNode;
import chenyibin.leetcode.common.ListNodeMaker;
import chenyibin.leetcode.common.ListNodeUtils;

public class TestSwapNodesInPairs {

	SwapNodesInPairs objut;
	
	@Before
	public void setUp()
	{
		objut = new SwapNodesInPairs();
	}
	
	@After
	public void tearDown()
	{
		objut = null;
	}
	
	@Test
	public void testSwapOneElement()
	{
		ListNode subject  = ListNodeMaker.make(1);
		ListNode expected = ListNodeMaker.make(1);
		
		objut.swapPairs(subject);
		
		assertTrue(ListNodeUtils.equals(expected, subject));
	}
	
	@Test
	public void testSwapTwoElements()
	{
		ListNode expected = ListNodeMaker.make(2,1);
		
		ListNode subject = objut.swapPairs(ListNodeMaker.make(1,2));
		
		assertTrue(ListNodeUtils.equals(expected, subject));
	}
	
	@Test
	public void testSwapThreeElements()
	{
		ListNode expected = ListNodeMaker.make(2,1,3);
		
		ListNode subject = objut.swapPairs(ListNodeMaker.make(1,2,3));
		
		assertTrue(ListNodeUtils.equals(expected, subject));
	}
	
	@Test
	public void testSwapFourElements()
	{
		ListNode expected = ListNodeMaker.make(2,1,4,3);
		
		ListNode subject = objut.swapPairs(ListNodeMaker.make(1,2,3,4));
		
		assertTrue(ListNodeUtils.equals(expected, subject));
	}
	
	@Test
	public void testSwapFiveElements()
	{
		ListNode expected = ListNodeMaker.make(2,1,4,3,5);
		
		ListNode subject = objut.swapPairs(ListNodeMaker.make(1,2,3,4,5));
		
		assertTrue(ListNodeUtils.equals(expected, subject));
	}
				
}
