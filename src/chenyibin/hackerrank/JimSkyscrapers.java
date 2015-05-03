package chenyibin.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * <strong>Problem:</strong></br>
 * We have in total N skyscrapers aligned from left to right.</br>
 * Each skyscraper x is associated with a height h_x.</br>
 * A flying route can be described as (i,j) with i != j.</br>
 * The route (i,j) is valid if each of the skyscrapers i,i+1,...,j-1,j</br>
 * is not strictly greater than h_i and if h_i = h_j.</br>
 * Note that if (i,j) is valid then so is (j,i).</br>
 * 
 * @author Yibin Chen
 */
public class JimSkyscrapers
{
	private File inputFile;
	
	int numSkyScrapers;
	int[] heights;
	
	public JimSkyscrapers()
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

	private void readInput()
	{
		Scanner scr = createInputScanner();
		this.numSkyScrapers = scr.nextInt();
		this.heights = new int[this.numSkyScrapers];
		for (int i = 0; i < this.numSkyScrapers; ++i) {
			this.heights[i] = scr.nextInt();
		}
	}

	private void solve()
	{
		NavigableMap<Integer,Integer> seenScrapers
			= new TreeMap<Integer,Integer>();

		long countRoutes = 0L;
		for (int i = 0; i < this.numSkyScrapers; ++i)
		{
			int height = this.heights[i];
			
			for (Entry<Integer,Integer> lowerScrapers = seenScrapers.lowerEntry(height);
				lowerScrapers != null;
				lowerScrapers = seenScrapers.lowerEntry(height))
			{
				int numShorties = lowerScrapers.getValue();
				if (numShorties > 1) {
					// Combination calculation: numShorties choose 2
					countRoutes += numShorties * (numShorties - 1);
				}
				// Forget scrapers shorter than this one
				seenScrapers.remove(lowerScrapers.getKey());
			}
			
			Integer hazSeen = seenScrapers.get(height);
			if (hazSeen == null) {
				seenScrapers.put(height, 1);
			} else {
				seenScrapers.put(height, hazSeen + 1);
			}
		}
		
		for (Entry<Integer,Integer> seen : seenScrapers.entrySet())
		{
			// This needs to be a long due to large testcases
			long seenCount = seen.getValue();
			if (seenCount > 1) {
				countRoutes += seenCount * (seenCount - 1);
			}
		}
		
		System.out.println(countRoutes);
	}
		
	public static void main(String[] args)
	{
		JimSkyscrapers solver = new JimSkyscrapers();
		solver.readInput();
		solver.solve();
	}
}
