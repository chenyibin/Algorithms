package chenyibin.hackerrank.easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickSort {
	private File inputFile;
	
	int size;
	int[] arr;
	
	
	public QuickSort()
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
		this.size = scr.nextInt();
		
		this.arr = new int[this.size];
		for (int i = 0; i < this.size; ++i)
		{
			this.arr[i] = scr.nextInt();
		}
	}

	private void solve() {
		quickSort(0, this.size - 1);
	}
	
	private void quickSort(int start, int end)
	{
		if (start >= end) {
			return;
		}

		int pivoted = partition(start, end);
		printArray();
		quickSort(start, pivoted-1);
		quickSort(pivoted+1, end);
	}
	
	private int partition(int start, int end)
	{
		int pivot = this.arr[end];
		
		int store = start;
		
		for (int i = start; i < end; ++i)
		{
			if (this.arr[i] < pivot) {
				swap(i, store);
				++store;
			}
		}
		swap(end, store);
		return store;
	}
	
	private void swap(int first, int last)
	{
		if (first == last)
			return;
		int tmp = this.arr[first];
		this.arr[first] = this.arr[last];
		this.arr[last] = tmp;
	}
	
	private void printArray()
	{
		boolean first = true;
		StringBuilder blder = new StringBuilder();
		for (int i : this.arr) {
			if (first) first = false;
			else blder.append(' ');
			blder.append(i);
		}
		System.out.println(blder.toString());
	}
		
	public static void main(String[] args)
	{
		QuickSort solver = new QuickSort();
		solver.readInput();
		solver.solve();
	}
}
