package chenyibin.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * <strong>Problem:</strong></br>
 * Given an array A of integers figure out the number of possible</br>
 * ways to select elements from A such that their sum is even.</br>
 * Print the number modulo 1000000007.</br>
 * 
 * @author Yibin Chen
 */
public class ChocolateFiesta {
	private static final long MOD = 1000000007;
	private File inputFile;
	private int setSize;
	private int[] inputSet;
	
	public ChocolateFiesta()
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

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	private void readInput() {
		Scanner scr = createInputScanner();
		this.setSize  = scr.nextInt();
		this.inputSet = new int[this.setSize];
		for (int i = 0; i < this.setSize; ++i) {
			this.inputSet[i] = scr.nextInt();
		}
	}

	private void solve() {
		
		long countSumEven = 0;
		long countSumOdd  = 0;

		for (int i : inputSet)
		{
			if (i % 2 == 0) {
				countSumEven += (countSumEven + 1);
				countSumOdd  += countSumOdd;
			} else {
				long oldEven  = countSumEven;
				countSumEven += countSumOdd;
				countSumOdd  += (oldEven + 1);
			}
			countSumEven %= MOD;
			countSumOdd  %= MOD;  
		}
		System.out.println(countSumEven);
	}
		
	public static void main(String[] args)
	{
		ChocolateFiesta solver = new ChocolateFiesta();
		solver.readInput();
		solver.solve();
	}
}
