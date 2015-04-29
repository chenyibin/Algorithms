package chenyibin.leetcode;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestThreeSum {

	ThreeSum threeSum;
	
	@Before
	public void setUp() {
		this.threeSum = new ThreeSum();
	}
	
	@After
	public void tearDown() {
		this.threeSum = null;
	}
	
	@Test
	public void testThreeSumSample()
	{
		int[] subject = {-1,0,1,2,-1,-1,-1,-4};
		List<List<Integer>> result = this.threeSum.threeSum(subject);
		
		for (List<Integer> one : result) {
			String show = "[" + StringUtils.join(one, ",") + "]";
			System.out.println(show);
		}
	}
}
