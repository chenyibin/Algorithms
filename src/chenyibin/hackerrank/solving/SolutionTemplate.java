package chenyibin.hackerrank.solving;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SolutionTemplate {
	private File inputFile;
	
	public SolutionTemplate()
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
		// Problem specific readInput()
	}

	private void solve() {
		// Problem specific solve()
	}
		
	public static void main(String[] args)
	{
		SolutionTemplate solver = new SolutionTemplate();
		solver.readInput();
		solver.solve();
	}
}
