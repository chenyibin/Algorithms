package chenyibin.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <strong>Problem:</strong></br>
 * Given an array X = [x1,x2,..,xm].
 * Find the number of all pairs of subsequences of X
 * A={xa1,xa2,...,xan} and B={xb1,xb2,...,xbn} such that:
 * <li>A and B are of same length</li>
 * <li>sum(A) + sum(B) = r</li>
 * <li>sum(A) - sum(B) = s</li>
 *
 * @author Yibin Chen
 */
public class WetShark
{
	private static final int MOD = 1000000007;

	private File  inputFile;
	private int[] inputArray;
	private int   inputLength;
	private int targetSum;
	private int targetDiff;
	
	private long[][] subseqCounter;
	
	public WetShark()
	{
		this.inputFile  = null;
		this.inputArray = null;
		this.targetSum  = 0;
		this.targetDiff = 0;
	}
	
	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
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
				System.err.println("Could not find input file " + this.getInputFile().toString());
				System.exit(1);
			}
		}
		return scr;
	}
	
	private void readInput()
	{
		Scanner scr = createInputScanner();
		
		this.inputLength = scr.nextInt();
		this.targetSum   = scr.nextInt();
		this.targetDiff  = scr.nextInt();
		
		this.inputArray = new int[this.inputLength];
		for (int i = 0; i < this.inputLength; ++i) {
			this.inputArray[i] = scr.nextInt();
		}
	}
	
	private void solve()
	{
		// The subsequence counter subseqCounter[n][s] keeps
		// tracks of how many subsequences of length n sum to s
		this.subseqCounter = new long[this.inputLength + 1][this.targetSum + 1];
		
		// Initially the first row is of form [1,0,0,...,0].
		// With X = [] there is exactly one way of obtaining a
		// sum of 0 using a subsequence of size 0 and no way
		// of obtaining any other sum since the subarray is empty.
		this.subseqCounter[0][0] = 1;
		
		for (int i = 0; i < this.inputLength; ++i) {
			updateSubsequenceTableWithElement(i);
		}
		
		int answer = 0;
		int sumRS = this.targetDiff + this.targetSum;
		for (int i = 1; i <= this.inputArray.length; ++i)
		{
			// For a given row in the table pretend that
			// the column index is sumA, then solve for sumB
			for (int sumA = 0; sumA <= this.targetSum; ++sumA)
			{
				// since r = sumA + sumB and s = sumA - sumB then
				// r + s = sumA + sumA 
				if (sumA + sumA == sumRS) {
					// we can solve for sumB either using:
					// sumB = r - sumA or sumB = sumA - s
					int sumB = this.targetSum - sumA;
					answer += subseqCounter[i][sumA] * subseqCounter[i][sumB] % MOD;
					answer %= MOD;
				}
			}
		}
		System.out.println(answer);
	}

	private void updateSubsequenceTableWithElement(int elementIndex)
	{
		int element = this.inputArray[elementIndex];
		// Loop through every possible subsequence length and pretend
		// that we are adding the new element to an existing subsequence sum.
		// This loop needs to start at the maximum subsequence length before the new
		// element (which happens to be the elementIndex) and count downwards
		// to avoid double counting the new element.
		for (int subsequenceLength = elementIndex; subsequenceLength >= 0; --subsequenceLength) {
			// loop through every possible sum smaller than targetSum 
			for (int subseqSum = 0; subseqSum <= this.targetSum; ++subseqSum)
			{
				if(this.subseqCounter[subsequenceLength][subseqSum] <= 0)
					continue;
				int newSubseqSum = element + subseqSum;
				if (newSubseqSum > this.targetSum)
					continue;
				// newSubseqSum can be obtained by adding element to every
				// subsequence which sum to subseqSum.. so we update the
				// count for the length-sum pair in the subsequence counter table.
				this.subseqCounter[subsequenceLength + 1][newSubseqSum] +=
					this.subseqCounter[subsequenceLength][subseqSum];
				this.subseqCounter[subsequenceLength + 1][newSubseqSum] %= MOD;
			}
			/*
			System.out.println(String.format(
				"Subsequence Table after adding element %s and processing row %s:",
				elementIndex, subsequenceLength));
			System.out.println(
				subsequenceTableToString(elementIndex + 2, this.targetSum + 1));
			*/
		}
	}
	
	public String subsequenceTableToString(int rows, int cols)
	{
		StringBuilder result = new StringBuilder();
		boolean firstRow = true;
		for (int row = 0; row < rows; ++row) {
			if (firstRow) {
				firstRow= false;
			} else {
				result.append("\n");
			}
			result.append("[");
			boolean firstColumn = true;
			for (int col = 0; col < cols; ++col) {
				if (firstColumn) {
					firstColumn= false;
				} else {
					result.append(",");
				}
				result.append(this.subseqCounter[row][col]);
			}
			result.append("]");
		}
		return result.toString();
	}
	
	public static void main(String[] args)
	{
		WetShark solver = new WetShark();
		solver.readInput();
		solver.solve();
	}
}
