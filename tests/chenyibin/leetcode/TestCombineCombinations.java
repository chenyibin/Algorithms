package chenyibin.leetcode;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCombineCombinations {
	
	CombineCombinations solver;

	@Before
	public void setUp()
	{
		this.solver = new CombineCombinations();
	}
	
	@After
	public void tearDown()
	{
		this.solver = null;
	}
	
	@Test
	public void testSimpleCase() {
		solver.combine(2, 1);
	}
}
