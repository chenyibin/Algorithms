package chenyibin.leetcode;

/**
 * Problem #312 from leetcode.com:
 * Given n balloons, indexed from 0 to n-1.
 * Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[i-1] * nums[i] * nums[i+1] coins.
 * After the burst, balloon i is removed and ballon i-1 and i+1 become adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * 
 * Note:
 * (1) You may imagine nums[-1] = nums[n] = 1.
 *     They are not real therefore you can not burst them.
 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 */
public class BurstBalloons {
    
    int[][] memory;
    int[] burstNums;

    public int maxCoins(int[] nums) {
        burstNums = new int[nums.length + 2];
        
        int n = 0;
        burstNums[n++] = 1;
        for (int num : nums) {
            if (num > 0) {
                burstNums[n++] = num;
            }
        }
        burstNums[n] = 1;
        
        memory = new int[n+1][n+1];
        
        return bestBurstValue(0, n);
    }

    /**
     * Find the maximum coins you can get by bursting balloons between
     * lo and hi without actually bursting lo and hi
     * @param lo
     * @param hi
     * @return
     */
    private int bestBurstValue(int lo, int hi)
    {
        if (lo + 1 == hi) {
            return 0;
        }
        
        if (memory[lo][hi] > 0) {
            return memory[lo][hi];
        }
        
        int result = 0;
        
        // Note that here we aren't picking the first balloon to burst
        // we are picking the last ballon that will burst after all the other
        // ones are gone!
        for (int lastBurst = lo + 1; lastBurst < hi; ++lastBurst)
        {
            int coinsForLastBurst = burstNums[lo] * burstNums[lastBurst] * burstNums[hi];
            int coinsForBalloonsLeft = bestBurstValue(lastBurst, hi);
            int coinsForBalloonsRight = bestBurstValue(lo, lastBurst);
            int coinsForAllBursts = coinsForLastBurst + coinsForBalloonsLeft + coinsForBalloonsRight;
            result = Math.max(result, coinsForAllBursts);
        }
        memory[lo][hi] = result;
        return result;
    }
}
