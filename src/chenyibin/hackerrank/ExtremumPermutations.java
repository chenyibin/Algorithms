package chenyibin.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <strong>Problem:</strong></br>
 * Consider a permutation P = {p1, p2, ..., pN} of the set N = {1, 2, 3, ..., N}</br>
 * P is called a magic set of it satisfies both of the following constraints:
 *   <li>Given a set A of K integers, the elements in positions a1, a2, ..., aK</br>
 *   are less than their adjacent elements, i.e., P[ai-1] > P[ai] < P[ai+1]</br>
 *   </li>
 *   <li>Given a set B of L integers, elements in positions b1, b2, ..., bL</br>
 *   are greater than their adjacent elements, i.e., P[bi-1] < P[bi] > P[bi+1]</br>
 *   </li></br>
 * Calculate how many such magic sets there are?</br>
 * </br>
 * <strong>Solution:</strong></br>
 * Create a dynamic programming table where each entry dp[row][col] counts</br>
 * the number of permutations of size 'row' ends in the number given by 'col'.</br>
 * Let's just consider the case where there are no constraints in A[...] and B[...].</br>
 * For instance, the final dp table given N = 4:</br>
 * 0 0 0 0 0</br>
 * 0 1 0 0 0</br>
 * 0 1 1 0 0</br>
 * 0 2 2 2 0</br>
 * 0 6 6 6 6</br>
 * </br>
 * Looking at dp[3][2]=2 for instance we can determine that</br>
 * the number of permutations of length 3 ending in the number 2 is 2.</br>
 * We can calculate a given row in two steps:</br>
 * <li>First determine the number of permutations for each ending number</br>
 * such that the previous number is greater than the ending number.
 * </li>
 * <li>Then determine the number of permutations for each ending number</br>
 * such that the previous number is smaller than the ending number.
 * </li>
 * Sum up those two vectors to obtain the full row.</br>
 * 
 * For row 4 in the above example the greater than vector is:</br>
 * 0 6 4 2 0</br>
 * Each element vec_gt[i] is the sum of dp[row-1][i..N]
 * The less than vector is:</br>
 * 0 0 2 4 6</br>
 * Each element vec_lt[i] is the sum of dp[row-1][1..i]
 * Combining vec_gt and vec_lt we obtain:</br>
 * 0 6 6 6 6</br>
 * (Best to verify this by drawing up some examples)</br>
 * </br>
 * The key here is realizing that each new permutation is obtained by putting the new number</br>
 * at the end of the size-1 permutation and then swapping it with the desired ending number.</br>
 * In the above example the last new number is 4</br>
 * The new number is always greater than any number currently existing in the permutation</br>
 * 
 * Applying the constraints is then a simple matter of omitting either</br>
 * vec_gt or vec_lt when calculating each row in the dp table.</br>
 * 
 * The total is obtained by summing up each element in the last row of the dp table.</br>
 * 
 * @author Yibin Chen
 */
public class ExtremumPermutations {
	private File inputFile;
	
	int numElementsN;
	int sizeA;
	int sizeB;
	int[] arrA;
	int[] arrB;
	
	boolean debug = false;
	
	public static final long MOD = 1000000007;
	
	public long add(long one, long two)
	{
		return (one + two) % MOD;
	}
	
	public ExtremumPermutations()
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

	private void readInput() {
		Scanner scr = createInputScanner();
		this.numElementsN = scr.nextInt();
		this.sizeA = scr.nextInt();
		this.sizeB = scr.nextInt();
		this.arrA = new int[this.sizeA];
		this.arrB = new int[this.sizeB];
		for (int i = 0; i < this.sizeA; ++i) {
			this.arrA[i] = scr.nextInt();
		}
		for (int j = 0; j < this.sizeB; ++j) {
			this.arrB[j] = scr.nextInt();
		}
	}

	private void solve()
	{
		int nPlusOne = this.numElementsN + 1;

		// boolean array defaults everything to false
		boolean[] lookupA = new boolean[nPlusOne];
		boolean[] lookupB = new boolean[nPlusOne];
		for (int i = 0; i < this.sizeA; ++i) {
			lookupA[this.arrA[i]] = true;
		}
		for (int j = 0; j < this.sizeB; ++j) {
			lookupB[this.arrB[j]] = true;
		}
		
		if (noSolution(lookupA, lookupB)) {
			System.out.println("0");
			return;
		}
		
		long[][] dp = new long[nPlusOne][nPlusOne];
		
		dp[1][1] = 1;
		
		int prevRow = 1;
		// A means adjacent numbers are greater
		// B means adjacent numbers are smaller
		for (int row = 2; row < nPlusOne; ++row)
		{
			// Calculate the number of permutations P of size 'row'
			// where P[row-1] > P[row] and the last number is the column index
			if (!lookupA[prevRow] && !lookupB[row])
			{
				long sum = dp[prevRow][prevRow];
				for (int incEndingNum = prevRow; incEndingNum >= 1; --incEndingNum)
				{
					dp[row][incEndingNum] = add(dp[row][incEndingNum], sum);
					sum = add(sum, dp[prevRow][incEndingNum - 1]);
				}
			}
			
			printArray("Mid:", dp);
			
			// Ditto for P[row-1] < P[row]
			if (!lookupB[prevRow] && !lookupA[row])
			{
				long sum = dp[prevRow][1];
				for (int decEndingNum = 2; decEndingNum <= row; ++decEndingNum)
				{
					dp[row][decEndingNum] = add(dp[row][decEndingNum], sum);
					sum = add(sum, dp[prevRow][decEndingNum]);
				}
			}
			printArray("End:",dp);
			prevRow = row;
		}
		
		long total = 0;
		for (int col = 0; col < nPlusOne; ++col)
		{
			total = add(total, dp[this.numElementsN][col]);
		}
		System.out.println(total);
	}
	
	private boolean noSolution(boolean[] lookupA, boolean[] lookupB)
	{
		// Note that problem constraint is that
		// 2 <= sizeA, sizeB < N
		int prev = 0;
		for (int i = 1; i <= this.numElementsN; ++i)
		{
			if (lookupA[prev] && lookupA[i]) {
				return true;
			}
			if (lookupB[prev] && lookupB[i]) {
				return true;
			}
			if (lookupA[i] && lookupB[i]) {
				return true;
			}
			prev = i;
		}
		return false;
	}

	public void printArray(String label, long[][] dp)
	{
		if (this.debug == false)
			return;
		
		System.out.println(label);
		for (int i = 0; i < dp.length; ++i)
		{
			StringBuilder builder = new StringBuilder();
			boolean first = true;
			for (int j = 0; j < dp[i].length; ++j)
			{
				if (first) first = false;
				else builder.append(' ');
				builder.append(dp[i][j]);
			}
			System.out.println(builder.toString());
		}
		System.out.println();
	}
		
	public static void main(String[] args)
	{
		ExtremumPermutations solver = new ExtremumPermutations();
		solver.readInput();
		solver.solve();
	}
}
