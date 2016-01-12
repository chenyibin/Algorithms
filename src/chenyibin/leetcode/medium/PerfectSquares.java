package chenyibin.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem #279 on leetcode.com:
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n. 
 * @author Yibin Chen
 */
public class PerfectSquares
{
    static List<Integer> mem = new ArrayList<>();
    static List<Integer> squares = new ArrayList<>();
    static {
        mem.add(0);
        squares.add(0);
    }
    
    public int numSquares(int n)
    {
        if (n <= 1) {
            return (n < 1) ? 0 : 1;
        }
        
        if (n < mem.size()) {
            return mem.get(n);
        }
        
        for (int i = mem.size(); i <= n; ++i)
        {
            int min = Integer.MAX_VALUE;
            for (int root = 1, square = 1;
                square <= i;
                square = getSquare(++root))
            {
                min = Math.min(min, mem.get(i - square) + 1);
            }
            mem.add(min);
        }
        
        return mem.get(n);
    }
    
    private int getSquare(int num) {
        if (num < squares.size()) {
            return squares.get(num);
        }
        for (int i = squares.size(); i <= num; ++i) {
            squares.add(i * i);
        }
        return squares.get(num);
    }
}
