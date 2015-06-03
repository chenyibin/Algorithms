package chenyibin.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * <strong>Problem:</strong></br>
 * A subsequence is a sequence that can be derived from another sequence</br>
 * by deleting some elements without changing the order of the remaining elements.</br>
 * Longest common subsequence (LCS) of 2 sequences is a subsequence,</br>
 * with maximal length, which is common to both the sequences.</br>
 * 
 * @author Yibin Chen
 */
public class LongestCommonSubsequence {
	private File inputFile;
	
	private int sizeA;
	private int sizeB;
	private int[] a;
	private int[] b;
	
	public LongestCommonSubsequence()
	{
		this.inputFile = null;
	}
	
	public Scanner createInputScanner()
	{
		Scanner scr = null;
		if (this.getInputFile() == null) {
			scr = new Scanner(System.in);
		} else {
			try {
				scr = new Scanner(this.getInputFile());
			} catch (FileNotFoundException e) {
				System.err.println("Could not find input file " +
					this.getInputFile().toString());
				System.exit(1);
			}
		}
		return scr;
	}

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = new File(inputFile);
	}

	private void readInput()
	{
		Scanner scr = createInputScanner();
		this.sizeA = scr.nextInt();
		this.sizeB = scr.nextInt();
		this.a = new int[this.sizeA];
		this.b = new int[this.sizeB];
		
		for (int i = 0; i < sizeA; ++i)
		{
			this.a[i] = scr.nextInt();
		}
	
		for (int j = 0; j < sizeB; ++j)
		{
			this.b[j] = scr.nextInt();
		}

	}

	private void solve()
	{
		// Build a dynamic programming table such that dyn[i][j] stores
		// the length of the longest subsequence for a[0..i] and b[0..j]
		int[][] dyn = new int[this.sizeA + 1][this.sizeB + 1];
		
		for (int i = 1; i <= this.sizeA; ++i)
		{
			for (int j = 1; j <= this.sizeB; ++j)
			{
				int isub1 = i - 1;
				int jsub1 = j - 1;
				dyn[i][j] = this.a[isub1] == this.b[jsub1]
					? dyn[isub1][jsub1] + 1
					: Math.max(dyn[i][jsub1], dyn[isub1][j]);
			}
		}
		
		int bi = this.sizeA;
		int bj = this.sizeB;
		int nextbi = bi - 1;
		int nextbj = bj - 1;
		
		LinkedList<Integer> list = new LinkedList<>();
		while (bi != 0 && bj != 0)
		{
			if (this.a[nextbi] == this.b[nextbj]) {
				list.add(this.a[nextbi]);
				bi = nextbi;
				bj = nextbj;
				--nextbi;
				--nextbj;
			}
			else if (dyn[bi][nextbj] > dyn[nextbi][bj])
			{
				bj = nextbj;
				--nextbj;
			} else {
				bi = nextbi;
				--nextbi;
			}
		}
		Iterator<Integer> outputIter = list.descendingIterator();
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		while (outputIter.hasNext())
		{
			if (first) {
				first = false;
			} else {
				builder.append(' ');
			}
			builder.append(outputIter.next());
		}
		System.out.println(builder.toString());
	}
		
	public static void main(String[] args)
	{
		LongestCommonSubsequence solver = new LongestCommonSubsequence();
		solver.readInput();
		solver.solve();
	}
}
