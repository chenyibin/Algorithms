package chenyibin.hackerrank.easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InsertionSortPartOne {
	private File inputFile;
	
	private int arraySize;
	private int[] array;
	
	public InsertionSortPartOne()
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
		this.arraySize = scr.nextInt();
		this.array = new int[this.arraySize];
		for (int i = 0; i < this.arraySize; ++i)
		{
			this.array[i] = scr.nextInt();
		}
	}

	private void solve() {
		if (this.arraySize == 1) {
			printArray();
			return;
		}
		
		
		int badPos = this.arraySize - 1;
		int badInt = this.array[badPos];
		int nextPos = badPos - 1;
		
		while (nextPos >= 0 && this.array[nextPos] > badInt)
		{
			this.array[badPos] = this.array[nextPos];
			badPos = nextPos;
			--nextPos;
			printArray();
		}
		this.array[badPos] = badInt;
		printArray();
	}
	
	private void printArray()
	{
		StringBuffer out = new StringBuffer();
		boolean first = true;
		for (int i = 0; i < this.arraySize; ++i)
		{
			if (first) first = false;
			else out.append(' ');
			out.append(this.array[i]);
		}
		System.out.println(out.toString());
	}
		
	public static void main(String[] args)
	{
		InsertionSortPartOne solver = new InsertionSortPartOne();
		solver.readInput();
		solver.solve();
	}
}
