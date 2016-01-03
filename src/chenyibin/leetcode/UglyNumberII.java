package chenyibin.leetcode;

/**
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
 * Write a program to find the n-th ugly number.
 * Note that 1 is treated as an ugly number.
 * @author Yibin Chen
 */
public class UglyNumberII {

    public int nthUglyNumber(int n) {
        if (n == 1) {
            return 1;
        }
        int[] uglies = new int[n];
        int mult2Idx = 0, mult3Idx = 0, mult5Idx = 0;
        int nextMult2 = 2, nextMult3 = 3, nextMult5 = 5;

        uglies[0] = 1;
        for (int i = 1; i < n; ++i) {
            int next = Math.min(Math.min(nextMult2, nextMult3), nextMult5);
            uglies[i] = next;
            if (nextMult2 == next) {
                nextMult2 = uglies[++mult2Idx] * 2;
            }
            if (nextMult3 == next) {
                nextMult3 = uglies[++mult3Idx] * 3;
            }
            if (nextMult5 == next) {
                nextMult5 = uglies[++mult5Idx] * 5;
            }
        }
        return uglies[n-1];
    }
}
