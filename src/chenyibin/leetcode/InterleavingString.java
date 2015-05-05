package chenyibin.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem #97 from leetcode.com:</br>
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.</br>
 * For instance "aadbbcbcac" is an interleaving of "aabcc" and "dbbca"</br>
 * 
 * <strong>Solution:</strong></br>
 * Perform a depth first search on a boolean table of height s1 and width s2.</br>
 * The value at position (i,j) is true if the substring s3[0...(i+j-1)] can be</br>
 * constructed through an interleaving of s1[0...i] and s2[0...j]</br>
 * We start the search at (0,0) since an empty string is the interleaving of two empty strings.</br>
 * Given that the table at (i,j) is true then position (i+1,j) is true if s3[i+j] == s1[i+1].</br>
 * Similarly position (i,j+1) is true if s3[i+j] == s2[j+1].</br>
 * s3 is an interleaving of s1 and s2 if the table at (s1.length(),s2.length()) is true.
 *
 * @author Yibin Chen
 */
public class InterleavingString
{
	boolean[][] tracker;
	String rowStr;
	String colStr;
	String target;
	
	private class PointStack
	{
		Deque<Integer> rowStack;
		Deque<Integer> colStack;

		public PointStack() {
			rowStack = new LinkedList<Integer>();
			colStack = new LinkedList<Integer>();
		}
		
		public void push(int row, int col)
		{
			rowStack.push(row);
			colStack.push(col);
		}
		
		public void pushDownIfMatch(int row, int col)
		{
			++row;
			if (row > rowStr.length())
				return;
				
			if (tracker[row][col] == true)
				return;
				
			char c = target.charAt(row + col - 1);
    		if (c == rowStr.charAt(row - 1)) {
    			tracker[row][col] = true;
    			push(row, col);
    		}
		}
		
		public void pushRightIfMatch(int row, int col)
		{
			++col;
			if (col > colStr.length())
				return;
				
			if (tracker[row][col] == true)
				return;
				
			char c = target.charAt(row + col - 1);
    		if (c == colStr.charAt(col - 1)) {
    			tracker[row][col] = true;
    			push(row, col);
    		}
		}
	
		public Integer peekRow() {
			return rowStack.peek();
		}
	
		public Integer peekCol() {
			return colStack.peek();
		}

		public void pop() {
			rowStack.pop();
			colStack.pop();
		}
		
		public boolean isEmpty() {
			return rowStack.isEmpty();
		}
	}
	
    public boolean isInterleave(String s1, String s2, String s3)
    {
        if (s3.length() != s1.length() + s2.length())
        	return false;
        	
    	this.rowStr = s1;
    	this.colStr = s2;
    	this.target = s3;
    
        // Java will initialize everything to false
        tracker = new boolean[rowStr.length() + 1][colStr.length() + 1];
        
        PointStack stack = new PointStack();
        stack.push(0, 0);
        tracker[0][0] = true;
        while (!stack.isEmpty())
        {
        	int row = stack.peekRow();
        	int col = stack.peekCol();
        	stack.pop();
        	stack.pushRightIfMatch(row, col);
        	stack.pushDownIfMatch(row, col);
        }
        
        return tracker[this.rowStr.length()][this.colStr.length()];
    }
}
