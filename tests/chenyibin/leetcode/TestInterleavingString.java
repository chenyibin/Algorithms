package chenyibin.leetcode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestInterleavingString {

	InterleavingString solver;

	@Before
	public void setUp()
	{
		this.solver = new InterleavingString();
	}
	
	@After
	public void tearDown()
	{
		this.solver = null;
	}
	
	@Test
	public void testSimpleCase() {
		boolean sol = solver.isInterleave("a", "", "a");
		assert(sol);
	}
}
