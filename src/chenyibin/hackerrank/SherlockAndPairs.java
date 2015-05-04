package chenyibin.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * <strong>Problem:</strong></br>
 * Find the number of pairs (i,j) in an array n
 * such that n[i] == n[j] but i !- j
 * 
 * @author Yibin Chen
 */
public class SherlockAndPairs {
	private File inputFile;

	
	public SherlockAndPairs()
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

	private void solve() {
		Scanner scr = createInputScanner();
		
		int numProblems = scr.nextInt();
		
		for (int i = 0; i < numProblems; ++i) {
			solveOneProblem(scr);
		}
	}
		
	private void solveOneProblem(Scanner scr) {
		int arrayLen = scr.nextInt();
		
		Map<Integer,Long> numFrequency = new HashMap<Integer,Long>();
		for (int i = 0; i < arrayLen; ++i) {
			int element = scr.nextInt();
			Long already = numFrequency.get(element);
			if (already == null) {
				numFrequency.put(element, 1L);
			} else {
				numFrequency.put(element, already + 1L);
			}
		}
		
		long pairsCount = 0;
		for (long elementCount : numFrequency.values()) {
			if (elementCount > 1) {
				pairsCount += elementCount * (elementCount - 1);
			}
		}
		System.out.println(pairsCount);
	}

	public static void main(String[] args)
	{
		SherlockAndPairs solver = new SherlockAndPairs();
		solver.solve();
	}
}
