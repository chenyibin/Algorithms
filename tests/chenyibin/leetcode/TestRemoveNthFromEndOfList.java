package chenyibin.leetcode;

import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import chenyibin.leetcode.common.ListNode;
import chenyibin.leetcode.common.ListNodeMaker;
import chenyibin.leetcode.common.ListNodeUtils;

public class TestRemoveNthFromEndOfList
{
	RemoveNthFromEndOfList objut; // Object Under Test
	
	@Before
	public void setUp()
	{
		objut = new RemoveNthFromEndOfList();
	}
	
	@After
	public void tearDown()
	{
		objut = null;
	}
	
	@Test
	public void testRemove1From1()
	{
		ListNode subject  = ListNodeMaker.make(1);
		ListNode result = objut.removeNthFromEnd(subject, 1);
		assertNull(result);
	}

	@Test
	public void testRemove2From2()
	{
		ListNode subject  = ListNodeMaker.make(1,2);
		ListNode expected = ListNodeMaker.make(2);
		
		ListNode result = objut.removeNthFromEnd(subject, 2);
		
		assert(ListNodeUtils.equals(expected, result));
	}
	
	@Test
	public void testRemove1From2()
	{
		ListNode subject  = ListNodeMaker.make(1,2);
		ListNode expected = ListNodeMaker.make(1);
		
		ListNode result = objut.removeNthFromEnd(subject, 1);
		
		assert(ListNodeUtils.equals(expected, result));
	}
	
	@Test
	public void testRemove1From6()
	{
		ListNode subject  = ListNodeMaker.make(1,2,2,3,4,5);
		ListNode expected = ListNodeMaker.make(1,2,2,3,4);
		
		ListNode result = objut.removeNthFromEnd(subject, 1);
		
		assert(ListNodeUtils.equals(expected, result));
	}
	
	@Test
	public void testRemove3From6()
	{
		ListNode subject  = ListNodeMaker.make(1,2,2,3,4,5);
		ListNode expected = ListNodeMaker.make(1,2,2,4,5);
		
		ListNode result = objut.removeNthFromEnd(subject, 1);
		
		assert(ListNodeUtils.equals(expected, result));
	}

	@Test
	public void testRemove6From6()
	{
		ListNode subject  = ListNodeMaker.make(1,2,2,3,4,5);
		ListNode expected = ListNodeMaker.make(2,2,3,4,5);
		
		ListNode result = objut.removeNthFromEnd(subject, 1);
		
		assert(ListNodeUtils.equals(expected, result));
	}
		
	@Test(expected=IllegalArgumentException.class)
	public void testThrowsOutOfBoundsNLarge()
	{
		ListNode subject  = ListNodeMaker.make(1,2);
		objut.removeNthFromEnd(subject, 3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testThrowsOutOfBoundsNNegative()
	{
		ListNode subject  = ListNodeMaker.make(1,2);
		objut.removeNthFromEnd(subject, -1);
	}
}
