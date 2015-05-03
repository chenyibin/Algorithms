package chenyibin.hackerrank;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * <strong>Problem:</strong></br>
 * Given N integers, count the number of pairs of integers</br>
 * whose difference is K.
 * 
 * @author Yibin Chen
 */
public class SimplePairs {

    static int pairs(int[] a,int k) {
    	Set<Integer> a_set = new HashSet<Integer>();
    	
        for (int one : a) {
            a_set.add(one);
        }
        int count = 0;
        for (int one : a) {
        	int kdiff = one - k;
        	if (a_set.contains(kdiff)) {
        		++count;
        	}
        }
        return count;
    }

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        long row = 1;
        long ten = (row / 2) * 10;
        System.out.println(ten);
        System.exit(0);
        
        Scanner in = new Scanner(System.in);
        int res;
        
        String n = in.nextLine();
        String[] n_split = n.split(" ");
        
        int _a_size = Integer.parseInt(n_split[0]);
        int _k = Integer.parseInt(n_split[1]);
        
        int[] _a = new int[_a_size];
        int _a_item;
        String next = in.nextLine();
        String[] next_split = next.split(" ");
        
        for(int _a_i = 0; _a_i < _a_size; _a_i++) {
            _a_item = Integer.parseInt(next_split[_a_i]);
            _a[_a_i] = _a_item;
        }
        
        res = pairs(_a,_k);
        System.out.println(res);

    }
}
