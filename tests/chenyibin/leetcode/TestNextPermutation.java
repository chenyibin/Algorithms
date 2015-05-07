package chenyibin.leetcode;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestNextPermutation {
	NextPermutation solver;

	@Before
	public void setUp()
	{
		this.solver = new NextPermutation();
	}
	
	@After
	public void tearDown()
	{
		this.solver = null;
	}
	
	@Test
	public void testSimpleCase() {
		int[] testcase = {1,3,2};
		int[] expected = {2,1,3};
		solver.nextPermutation(testcase);
		
		assert(Arrays.equals(expected, testcase));
	}
}
