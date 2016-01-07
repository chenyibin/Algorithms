package chenyibin.web;

import java.util.Arrays;

/**
 * Given two binary arrays arr1[] and arr2[] of same size n.
 * Find length of the longest common span (i, j) where j >= i such that
 * arr1[i] + arr1[i+1] + ... + arr1[j] = arr2[i] + arr2[i+1] + ... + arr2[j].
 * @author Yibin Chen
 */
public class LongestSpanSameSumBinaryArrays
{
    /**
     * @param arr1 binary array with only 1s and 0s
     * @param arr2 binary array with only 1s and 0s
     * @return longest span with same sum
     */
    public int longestCommonSum(int[] arr1, int[] arr2)
    {
        int len = arr1.length;
        // Note that in the general case
        // this can be replaced by a HashTable
        int[] firstSeen = new int[len + len + 1];
        Arrays.fill(firstSeen, Integer.MIN_VALUE);
        
        // Key observation for this problem is:
        // The sum is same between i and j for the two arrays iff
        // prefixSum1(i) - prefixSum2(i) == prefixSum1(j) - prefixSum2(j)
        int sum1 = 0;
        int sum2 = 0;
        int longest = 0;
        for (int i = 0; i < len; ++i) {
            sum1 += arr1[i];
            sum2 += arr2[i];
            int diff = sum1 - sum2;
            
            if (diff == 0) {
                longest = i + 1;
                continue;
            }
            
            int index = len + diff;
            if (firstSeen[index] == Integer.MIN_VALUE) {
                firstSeen[index] = i;
            } else {
                longest = Math.max(longest, i - firstSeen[diff]);
            }

        }
        return longest;
    }
}
