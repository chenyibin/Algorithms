package chenyibin.hackerrank.solving;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Solution {
	private File inputFile;
	
	public Solution()
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
		// Problem specific readInput()
	}

	private void solve() {
		// Problem specific solve()
	}
		
	public static void main(String[] args)
	{
		Solution solver = new Solution();
		solver.readInput();
		solver.solve();
	}
}
