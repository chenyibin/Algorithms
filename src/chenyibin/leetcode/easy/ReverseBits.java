package chenyibin.leetcode.easy;

/**
 * Problem #190 on leetcode.com:
 * Reverse bits of a given 32 bits unsigned integer.
 * @author Yibin Chen
 */
public class ReverseBits {
    public int reverseBits(int n)
    {
        int result = 0;
        for (int i = 0; i < 32; ++i)
        {
            result <<= 1;
            result += n & 1;
            n >>>= 1;
        }
        return result;
    }
}
