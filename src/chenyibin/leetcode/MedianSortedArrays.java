package chenyibin.leetcode;

import java.util.Arrays;

/**
 * Problem #4 on leetcode.com:
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. 
 * @author Yibin Chen
 */
public class MedianSortedArrays {
    int a[];
    int b[];
    int halfhalf;
    boolean aEvenElements;
    boolean bEvenElements;
    
    public double findMedianSortedArrays(int nums1[], int nums2[])
    {
        if (nums1.length == 0) return medianOneArray(nums2);
        if (nums2.length == 0) return medianOneArray(nums1);

        // let a always be the shorter array
        if (nums1.length < nums2.length)
        {
            a = nums1;
            b = nums2;
        } else {
            b = nums1;
            a = nums2;
        }
        
        halfhalf = (a.length-1)/2 + (b.length-1)/2;
        aEvenElements = a.length % 2 == 0;
        bEvenElements = b.length % 2 == 0;

        return medianSortedArrays(0, a.length - 1);
    }

    private double medianSortedArrays(int aLower, int aUpper)
    {
        int aMid = (aLower + aUpper) >>> 2;
        int bMid = halfhalf - aMid;
        
        if (aUpper - aLower < 2) {
            int[] join = new int[(aEvenElements ? 4 : 5) + (bEvenElements ? 4 : 5)];
            int j = 0;

            for (int i = (aEvenElements? aMid-1 : aMid-2); i < aMid+3; i++) {
                join[j++] = (i < 0) ? Integer.MIN_VALUE : i < a.length ? a[i] : Integer.MAX_VALUE;
            }
            for (int i = (bEvenElements? bMid-1 : bMid-2); i < bMid+3; i++) {
                join[j++] = (i < 0) ? Integer.MIN_VALUE : i < b.length ? b[i] : Integer.MAX_VALUE;
            }
            Arrays.sort(join);
            return medianOneArray(join);
        }
        if (a[aMid] > b[bMid]) {
            return medianSortedArrays(aLower, aMid);
        } else {
            return medianSortedArrays(aMid, aUpper);
        }
    }
    
    private double medianOneArray(int[] one) {
        int mid = (one.length - 1) / 2;
        
        if (one.length % 2 == 0) {
            return (double)(one[mid] + one[mid+1])/2.0;
        } else {
            return (double)one[mid];
        }
    }
}
