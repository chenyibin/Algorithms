package chenyibin.leetcode;

public class BestTimetoBuyAndSellStockIII {

    public int maxProfit(int[] prices)
    {
        int k = 4;
        if (prices.length == 0) {
            return 0;
        }
        
        if (k == 0) {
            return 0;
        }
        
        k = Math.min(k, prices.length);
        
        int[] bestTrades = new int[k];
        for (int i = 0; i < bestTrades.length; ++i) {
            bestTrades[i] = Integer.MIN_VALUE;
        }
        
        int maxTradesIndex = k - 1;
        
        for (int tradeDay = 0; tradeDay < prices.length; ++tradeDay)
        {
            int tradeLimit = Math.min(maxTradesIndex, tradeDay);
            int todaysPrice = prices[tradeDay];
            
            boolean isSale = (tradeLimit % 2 == 0) ? false : true;
            for (int i = tradeLimit; i > 0; --i) {
                int currentBest = bestTrades[i];
                int bestWithCurrentTrade = isSale
                    ? bestTrades[i-1] + todaysPrice
                    : bestTrades[i-1] - todaysPrice;
                bestTrades[i] = Math.max(currentBest, bestWithCurrentTrade);
                isSale = !isSale;
            }
            bestTrades[0] = Math.max(bestTrades[0], -todaysPrice);
        }
        
        int maxProfit = 0;
        for (int tradeProfit : bestTrades) {
            maxProfit = Math.max(maxProfit, tradeProfit);
        }
        return maxProfit;
    }
}
