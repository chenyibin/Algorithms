package chenyibin.epi;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.google.common.collect.Lists;

public class TestMaximizeProfitPairs {

	@Test
	public void testTwoTrades()
	{
		List<Integer> prices = Lists.newArrayList(1,4,3,7,20);
		int trades = 2;

		MaximizeProfitPairs maxer = new MaximizeProfitPairs(prices, trades);
		
		TestCase.assertEquals(20, maxer.calculateMaxProfit());
	}
	
	@Test
	public void testMoreTrades()
	{
		List<Integer> prices = Lists.newArrayList(1,4,3,7,20,6,9,12,34,33);
		int trades = 4;

		MaximizeProfitPairs maxer = new MaximizeProfitPairs(prices, trades);

		TestCase.assertEquals(45, maxer.calculateMaxProfit());
	}
	
	@Test
	public void testThreeTradesExact()
	{
		List<Integer> prices = Lists.newArrayList(1,4,3,7,4,5);
		int trades = 3;

		MaximizeProfitPairs maxer = new MaximizeProfitPairs(prices, trades);

		TestCase.assertEquals(8, maxer.calculateMaxProfit());
	}	
}
