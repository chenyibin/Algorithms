package chenyibin.leetcode.medium;

public class BestTimeToBuyAndSellStock
{
    public int maxProfit(int[] prices)
    {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;
        for (int price : prices)
        {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }
}
