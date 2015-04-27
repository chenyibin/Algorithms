package chenyibin.leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLargestNumber
{
	LargestNumber objut; // Object Under Test
	
	@Before
	public void setUp()
	{
		objut = new LargestNumber();
	}
	
	@After
	public void tearDown()
	{
		objut = null;
	}
	
	@Test
	public void testExampleCase()
	{
		int[] example = {3,30,34,5,9};
		String result = objut.largestNumber(example);
		assertEquals("9534330", result);
	}
	
	@Test
	public void testSimplePrefix()
	{
		int[] example = {128,12};
		String result = objut.largestNumber(example);
		assertEquals("12812", result);
	}
	
	@Test
	public void testLongerCase()
	{
		int[] example = {1,2,4,8,16,32,64,128,256,512};
		String result = objut.largestNumber(example);
		assertEquals("8645124322562161281", result);
	}
	
	@Test
	public void testDoubleZero()
	{
		int[] example = {0,0};
		String result = objut.largestNumber(example);
		assertEquals("0", result);
	}
	
	@Test
	public void testNestedPrefix()
	{
		int[] example = {4543,4543454};
		String result = objut.largestNumber(example);
		assertEquals("45434544543", result);
	}
	
}
