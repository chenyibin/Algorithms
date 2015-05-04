package chenyibin.leetcode;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
		List<List<Integer>> sol = solver.combine(2, 1);
		assertEquals(2, sol.size());
	}
	
	@Test
	public void testLargerCase() {
		List<List<Integer>> sol = solver.combine(7, 3);
		assertEquals(35, sol.size());
	}
}
