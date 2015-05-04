package chenyibin.leetcode;

/**
 * Problem #11 from leetcode.com:</br>
 * Given n non-negative integers a1, a2, ..., an, where each ai represents</br>
 * the height of a line at position x=i, find the pair of lines such that</br>
 * the are of the container they form holds the most water.
 * @author Yibin Chen
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        
        int begin = 0;
        int end   = height.length - 1;
        int maxArea = 0;
        int area    = 0; 
        while (begin < end) {
        	int beginHeight = height[begin];
        	int endHeight   = height[end];
        	if (beginHeight > endHeight) {
        		area = endHeight * (end - begin);
        		// we no longer have to consider containers bound on the
        		// right by end since every one of them will be smaller
        		--end; 
        	} else {
        		area = beginHeight * (end - begin);
        		// we no longer have to consider containers bound on the
        		// left by begin since every one of them will be smaller
        		++begin;
        	}
        	maxArea = Math.max(area, maxArea);
        }
        
        return maxArea;
    }
}
