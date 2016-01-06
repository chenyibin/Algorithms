package chenyibin.leetcode.medium;

/**
 * Problem #134 on leetcode.com:
 * There are gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with no gase but an unlimited gas tank.
 * The amount of gas it takes to travel from station i to (i+i) is given by cost[i].
 * Find the location of the gas station where you can start a complete round-trip journey.
 * The solution is guaranteed to be unique.
 * @author Yibin Chen
 */
public class GasStation
{
    public int canCompleteCircuit(int[] gas, int[] cost) {
        
        int tank = 0;
        int total = 0;
        int start = 0;
        for (int i = 0; i < gas.length; ++i) {
            int gain = gas[i] - cost[i];
            if (tank < 0) {
                start = i;
                total += tank;
                tank = 0;
            }
            tank += gain;
        }
        total += tank;
        return (total < 0) ? -1 : start;
    }
}
