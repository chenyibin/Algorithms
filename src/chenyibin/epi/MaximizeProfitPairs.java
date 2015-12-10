package chenyibin.epi;

import java.util.ArrayList;
import java.util.List;

public class MaximizeProfitPairs
{
	List<Integer> prices;
	int trades;
	
	MaximizeProfitPairs(List<Integer> prices, int trades)
	{
		this.prices = prices;
		this.trades = trades;
	}
	
	int calculateMaxProfit()
	{
		int numIndividualTrades = trades * 2;
		List<Integer> maxProfits = new ArrayList<>(numIndividualTrades);
		for (int i = 0; i < numIndividualTrades; ++i) {
			maxProfits.add(Integer.MIN_VALUE);
		}
		
		int maxProfitsHighestIndex = numIndividualTrades - 1;
		
		for (int i = 0; i < this.prices.size(); ++i)
		{
			int todaysPrice = this.prices.get(i);
			
			int startIndex = Math.min(i, maxProfitsHighestIndex);
			int sign = (startIndex % 2 == 1) ? 1 : -1;
			for (int j = startIndex; j >= 0; --j)
			{
				int bestPreviousProfit = (j == 0) ? 0 : maxProfits.get(j - 1);
				int profitActToday = bestPreviousProfit + sign * todaysPrice;
				int bestPreviousSale = maxProfits.get(j);
				maxProfits.set(j, Math.max(profitActToday, bestPreviousSale));
				sign *= -1;
			}
		}
		
		return maxProfits.get(maxProfitsHighestIndex);
	}
}
