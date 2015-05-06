package chenyibin.leetcode;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestFindPeakElement {
	FindPeakElement solver;

	@Before
	public void setUp()
	{
		this.solver = new FindPeakElement();
	}
	
	@After
	public void tearDown()
	{
		this.solver = null;
	}
	
	@Test
	public void testSimpleCase() {
		int[] nums = {1,2,3,1};
		
		int sol = solver.findPeakElement(nums);
		assertEquals(2, sol);
	}
}
