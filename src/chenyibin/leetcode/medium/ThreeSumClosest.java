package chenyibin.leetcode.medium;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target)
    {
        if (nums.length <= 3) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            return sum;
        }
        
        Arrays.sort(nums);
        
        int lastIndex = nums.length - 1;
        int bestDiff = Integer.MAX_VALUE;
        int answer = 0;
        for (int first = 0; first < nums.length - 2; ++first)
        {
            int firstNum = nums[first];
            int left = first + 1;
            int right = lastIndex;
            while (left < right) {
                int leftNum = nums[left];
                int rightNum = nums[right];
                int sum = firstNum + leftNum + rightNum;
                int diff = Math.abs(target - sum);
                if (diff < bestDiff) {
                    bestDiff = diff;
                    if (diff == 0) {
                        return sum;
                    }
                    answer = sum;
                }
                if (sum < target) {
                    ++left;
                } else {
                    --right;
                }
            }
        }
        return answer;
    }
}
