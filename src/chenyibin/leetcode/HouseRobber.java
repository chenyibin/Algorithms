package chenyibin.leetcode;

/**
 * Problem #198 from leetcode.com:
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob without robbing adjacent houses.
 * 
 * @author Yibin Chen
 */
public class HouseRobber {

    public int rob(int[] nums) {
        int lootLastRobbed = 0;
        int lootLastNotRobbed = 0;
        
        for (int loot : nums) {
            int curRobbed = lootLastNotRobbed + loot;
            
            lootLastNotRobbed = Math.max(lootLastRobbed, lootLastNotRobbed);
            lootLastRobbed = curRobbed;
        }
        return Math.max(lootLastRobbed, lootLastNotRobbed);
    }
}
