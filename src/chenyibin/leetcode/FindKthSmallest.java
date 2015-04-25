package chenyibin.leetcode;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chenyibin.common.utils.NullOutputStream;

public class FindKthSmallest
{
	MaxiArray a;
	MaxiArray b;
	PrintStream debugStream;

	FindKthSmallest(int[] a, int[] b)
	{
		this.a = new MaxiArray(a);
		this.b = new MaxiArray(b);
		this.debugStream = new PrintStream(NullOutputStream.getStream());
	}
	
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
	public void printStatus(int ai, int bi, int k)
	{
		this.debugStream.println(String.format("Step: ai=%s, bi=%s, k=%s", ai, bi, k));
		this.debugStream.println(arrToString("A", this.a.arr, ai, k));
		this.debugStream.println(arrToString("B", this.b.arr, bi, k));
	}

	private void printInitialState()
	{
		this.debugStream.println("Init Conditions");
		this.debugStream.println(arrToString("A: ", this.a.arr, 0, this.a.arr.length));
		this.debugStream.println(arrToString("B: ", this.b.arr, 0, this.b.arr.length));
	}
	
	public void setStream(PrintStream s)
	{
		this.debugStream = s;
	}
	
	public int getKthSmallest(int k)
	{
		if (k > this.a.arr.length + this.b.arr.length)
		{
			throw new IllegalArgumentException("k cannot exceed sum of array sizes");
		}
		
		printInitialState();

		if (this.a.arr.length == 0)
		{
			return this.b.get(k);
		}
		if (this.b.arr.length == 0)
		{
			return this.a.get(k);
		}
		
		if (k == 1) {
			return Math.min(a.get(0), b.get(0));			
		}
		return getKthSmallestRecursion(0, 0, k);
	}

	private int getKthSmallestRecursion(int ai, int bi, int k)
	{
		printStatus(ai, bi, k);
		
		if (k == 2) {
			return get2ndSmallest(ai, bi);
		}
	
		int halfk = (k - 1) / 2;
		
		int newai = ai + halfk;
		int newbi = bi + halfk;
		int aval = a.get(newai);
		int bval = b.get(newbi);
		
		if (aval == bval) {
			return aval;
		} else if (aval < bval) {
			if (k % 2 == 0) {
				if (bval < a.get(newai+1)) return bval;
			} else {
				if (aval > b.get(newbi-1)) return aval;
			}
			return getKthSmallestRecursion(newai, bi, k - halfk);
		} else {
			if (k % 2 == 0) {
				if (aval < b.get(newbi+1)) return aval;
			} else {
				if (bval > a.get(newai-1)) return bval;
			}
			return getKthSmallestRecursion(ai, newbi, k - halfk);
		}
	}
	
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
		int k = 2;
		int[] aa = {1};
		int[] bb = {2,3};
		FindKthSmallest finder = new FindKthSmallest(aa, bb);
		finder.setStream(System.out);
		System.out.println(String.format("The %s-th smallest number is %s", k, finder.getKthSmallest(k)));
	}
}
