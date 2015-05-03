package chenyibin.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Given an array A={a1,a2,…,aN} of N elements, find the maximum possible sum of a
 * <li>Contiguous subarray and,</li>
 * <li>Non-contiguous (not necessarily contiguous) subarray.</li></br>
 * 
 * Empty subarrays/subsequences should not be considered.
 * 
 * @author Yibin Chen
 */
public class MaxSubArray
{
	private File inputFile = null;
	private int numProblems;
	private List<List<Integer>> problems;

	public MaxSubArray()
	{
		this.inputFile = null;
		this.numProblems = 0;
	}

	public File getInputFile() {
		return inputFile;
	}

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
		
	private void readInput()
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
		
		this.numProblems = scr.nextInt();
		this.problems = new ArrayList<List<Integer>>(this.numProblems);
		for (int i = 0; i < this.numProblems; ++i) {
			int problemSize = scr.nextInt();
			List<Integer> problem = new ArrayList<Integer>(problemSize);
			for (int j = 0; j < problemSize; ++j) {
				problem.add(scr.nextInt());
			}
			this.problems.add(problem);
		}
		
		scr.close();
	}

	private void solve() {
		for (List<Integer> oneProblem : this.problems) {
			solveOne(oneProblem);
		}
	}
	
	private void solveOne(List<Integer> problem) {
		
		long largestConsecSum = Long.MIN_VALUE;
		long largestTillHere = Long.MIN_VALUE;
		long maxSingleValue = Long.MIN_VALUE;
		long largestAny = 0;
		// If the array has no positive values the largest
		// non-consecutive sequence is the largest single value
		boolean hadPositive = false;
		for (int one : problem)
		{
			if (largestTillHere < 0 && largestTillHere < one) {
				largestTillHere = one;
			} else {
				largestTillHere += one;
			}
			
			largestConsecSum = Math.max(largestTillHere, largestConsecSum);
			
			if (one > 0) {
				hadPositive = true;
				largestAny += one;
			}
			maxSingleValue = Math.max(maxSingleValue, one); 
		}
		
		if (!hadPositive) {
			largestAny = maxSingleValue;
		}
		System.out.println(largestConsecSum + " " + largestAny);
	}


    public static void main(String[] args)
    {
    	MaxSubArray solution = new MaxSubArray();
    	solution.readInput();
    	solution.solve();
    }


}
