package chenyibin.leetcode.easy;

public class PowerOfTwo
{
    public boolean isPowerOfTwo(int n)
    {
        if (n <= 0) {
            return false;
        }
        int firstSetBit = n & -n;
        return (n == firstSetBit);
    }
}
