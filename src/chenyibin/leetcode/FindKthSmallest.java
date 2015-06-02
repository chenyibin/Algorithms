package chenyibin.leetcode;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chenyibin.yyc.utils.NullOutputStream;

/**
 * Finds the k-th smallest element in the union of two sorted arrays.</br>
 * The first step here is to realize that the k-th smallest element must.</br>
 * be in the subarray a[0..k] and b[0..k]..</br>
 * 
 * We then examine the midpoints a[mid_ai] and b[mid_bi] of each of these subarrays.</br>
 * The larger of these two (let's say it's a[mid_ai]) has either exactly k</br>
 * or more than k elements smaller than itself, so we can't remove the smaller</br>
 * elements in a[] from our search space.</br>
 * </br>
 * However, for smaller of these two (let's say its b[mid_bi]), the elements in</br>
 * b[0..mid_bi-1] are guaranteed to not contain the k-th smallest element.</br>
 * This is because the only candidate numbers that it can have less than itself</br>
 * are b[0..mid_bi-1] and a[0..mid_ai-1] which don't add up to k numbers.</br>
 * (there are some considerations for when k is odd vs even)</br>
 * Those elements can effectively pruned from our search space.</br>
 * </br>
 * In addition there are some early exit conditions that we can consider.</br>
 * When a[mid_ai] == b[mid_bi] then one of them is the k-th smallest element since</br>
 * there are exactly k elements smaller or equal to their value.</br>
 * When k is odd and b[mid_bi] > a[mid_ai] > b[mid_bi-1] then a[mid_ai] is the</br>
 * k-th smallest element because there are exactly k/2 elements smaller than</br>
 * b[mid_bi-1] in b[] and k/2 smaller than a[mid_ai] in a[].</br>
 * Similarly we can justify the other early exit conditions.</br>
 *
 * @author Yibin Chen
 */
public class FindKthSmallest
{
	MaxiArray a;
	MaxiArray b;
	
	// This is a tricky problem so let's add some debugging capabilities
	PrintStream debugStream;

	FindKthSmallest(int[] a, int[] b)
	{
		this.a = new MaxiArray(a);
		this.b = new MaxiArray(b);
		this.debugStream = new PrintStream(NullOutputStream.getStream());
	}
	
	/**
	 * The MaxiArray deals with the case where one array is much smaller than
	 * the other array and we'd like to pretend that the smaller array returns
	 * infinity when we are accessing an element that is actually out-of-bounds
	 */
	public static class MaxiArray
	{
		int[] arr;
		public MaxiArray(int[] arr)
		{
			this.arr = arr;
		}
		
		public int get(int i)
		{
			if (i >= arr.length) {
				return Integer.MAX_VALUE;
			}
			return arr[i];
		}
	}
	
	/**
	 * Debug function for printing the status of the search
	 */
	private void printStatus(int ai, int bi, int k)
	{
		this.debugStream.println(String.format("Step: ai=%s, bi=%s, k=%s", ai, bi, k));
		this.debugStream.println(arrToString("A", this.a.arr, ai, k));
		this.debugStream.println(arrToString("B", this.b.arr, bi, k));
	}
	
	/**
	 * Debug function for printing the initial state of the search
	 */
	private void printInitialState()
	{
		this.debugStream.println("Init Conditions");
		this.debugStream.println(arrToString("A: ", this.a.arr, 0, this.a.arr.length));
		this.debugStream.println(arrToString("B: ", this.b.arr, 0, this.b.arr.length));
	}
	
	/**
	 * Set this to System.out so we can see what's going on
	 * @param s
	 */
	public void setStream(PrintStream s)
	{
		this.debugStream = s;
	}
	
	public int getKthSmallest(int k)
	{
		if (k < 1) {
			throw new IllegalArgumentException("k must be greater than 0");
		}
		
		if (k > this.a.arr.length + this.b.arr.length) {
			throw new IllegalArgumentException("k cannot exceed sum of array sizes");
		}
		
		printInitialState();

		if (this.a.arr.length == 0) {
			return this.b.get(k);
		}
		if (this.b.arr.length == 0) {
			return this.a.get(k);
		}
		
		if (k == 1) {
			return Math.min(a.get(0), b.get(0));
		}
		return getKthSmallestRecursion(0, 0, k);
	}

	/**
	 * This function solves the <i>k-th</i> smallest in two sorted
	 * arrays problem recursively for k > 1. It doesn't deal properly
	 * with the case for k = 1 so that needs to be handled separately.
	 * @param ai
	 * @param bi
	 * @param k
	 * @return
	 */
	private int getKthSmallestRecursion(int ai, int bi, int k)
	{
		printStatus(ai, bi, k);
		
		if (k == 2) {
			return get2ndSmallest(ai, bi);
		}
	
		int half_k = (k - 1) / 2;
		
		int mid_ai = ai + half_k;
		int mid_bi = bi + half_k;
		int aval = a.get(mid_ai);
		int bval = b.get(mid_bi);
		
		if (aval == bval) {
			// Early exit condition
			return aval;
		} else if (aval < bval) {
			// Early exit condition
			if (k % 2 == 0) {
				if (bval < a.get(mid_ai+1)) return bval;
				// if k is even then aval cannot be k-th smallest
				++mid_ai; 
			} else {
				if (aval > b.get(mid_bi-1)) return aval;
			}
			// Continue the search
			return getKthSmallestRecursion(mid_ai, bi, k - half_k);
		} else {
			// Early exit condition
			if (k % 2 == 0) {
				if (aval < b.get(mid_bi+1)) return aval;
				// if k is even then bval cannot be k-th smallest
				++mid_bi;
			} else {
				if (bval > a.get(mid_ai-1)) return bval;
			}
			// Continue the search
			return getKthSmallestRecursion(ai, mid_bi, k - half_k);
		}
	}
	
	/**
	 * Being lazy and using a simple sort here since I don't want to deal
	 * with all the cases for getting the 2nd smallest in a 4-element duke out.
	 * Note that the MaxiArray is really handy here since sometimes the element
	 * a[ai+1] or b[ai+1] are out of bounds and the MaxiArray automatically puts
	 * them out of the running for second by returning Integer.MAX_VALUE.
	 * @param ai starting index into a[]
	 * @param bi starting index into b[]
	 * @return 2nd smallest element from union of subarrays a[ai..end] and b[bi..end]
	 */
	private int get2ndSmallest(int ai, int bi)
	{
		List<Integer> sortingList = new ArrayList<Integer>();
		sortingList.add(this.a.get(ai));
		sortingList.add(this.a.get(ai+1));
		sortingList.add(this.b.get(bi));
		sortingList.add(this.b.get(bi+1));
		Collections.sort(sortingList);	
		return sortingList.get(1);
	}
	

	public String arrToString(String label, int[] arr, int start, int len)
	{
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		builder.append(label + ": [");

		for (int i = start; i < start + len; ++i)
		{
			if (first) {
				first = false;
			} else {
				builder.append(", ");
			}
			if (i < arr.length) {
				builder.append(arr[i]);
			} else {
				builder.append("inf");
			}
		}
		builder.append(']');
		return builder.toString();
	}
	
	public static void main(String[] args)
	{
		int k = 6;
		int[] aa = {19,20,21,90,100,101,120,140,1110};
		int[] bb = {2,3,10};
		FindKthSmallest finder = new FindKthSmallest(aa, bb);
		finder.setStream(System.out);
		System.out.println(String.format("The %s-th smallest number is %s", k, finder.getKthSmallest(k)));
	}
}
