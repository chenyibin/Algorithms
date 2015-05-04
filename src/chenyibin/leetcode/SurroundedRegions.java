package chenyibin.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem #130 on leetcode.com:</br>
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.</br>
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.</br>
 * For example,</br>
 * X X X X</br>
 * X O O X</br>
 * X X O X</br>
 * X O X X</br>
 * 
 * After running your function, the board should be:</br>
 * X X X X</br>
 * X X X X</br>
 * X X X X</br>
 * X O X X</br>
 * 
 * <strong>Solution:</strong></br>
 * A region is alive only if it can be reached from the border.</br>
 * Perform a DFS from 'O's at the border and mark everything you</br>
 * find through the DFS as Live ('L'). The convert every 'L' regions</br>
 * into an 'O' region and every 'O' region into a 'X' region.
 * 
 * @author Yibin Chen
 */
public class SurroundedRegions
{
	char[][] board;
	int rows;
	int cols;
	
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
		
		public void pushIfO(int row, int col)
		{
			if (row < 0 || row >= rows || col < 0 || col >= cols)
				return;
				
    		if (board[row][col] == 'O') {
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
	
    public void solve(char[][] board)
    {
    	this.board = board;
    	if (this.rows <= 2) {
    	    return;
    	}
    	this.cols = board[0].length;
    	if (this.cols <= 2) {
    		return;
    	}
    	
    	PointStack stack = new PointStack();
    	for (int c = 0; c < cols; ++c) {
    		stack.pushIfO(0, c);
    		stack.pushIfO(rows-1, c);
    	}

    	for (int r = 0; r < rows; ++r) {
    		stack.pushIfO(r, 0);
    		stack.pushIfO(r, cols-1);
    	}
        	
    	while (!stack.isEmpty()) {
    		int row = stack.peekRow();
    		int col = stack.peekCol();
    		stack.pop();
    		if (board[row][col] == 'O') {
    			board[row][col] = 'L';
    		}
    		stack.pushIfO(row-1, col);
    		stack.pushIfO(row+1, col);
    		stack.pushIfO(row, col-1);
    		stack.pushIfO(row, col+1);
    	}
    	
    	for (int row = 0; row < this.rows; ++row) {
    		for (int col = 0; col < this.cols; ++col) {
    			if (board[row][col] == 'O') {
    				board[row][col] = 'X';
    			} else if (board[row][col] == 'L') {
    				board[row][col] = 'O';
    			}
    		}
    	}
    }
}
