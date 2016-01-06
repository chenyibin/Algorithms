package chenyibin.leetcode.medium;

/**
 * Problem #307 on leetcode.com:
 * Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val. 
 * @author Yibin Chen
 */
public class RangeSumQueryMutable
{
    private static class SegTreeNode
    {
        SegTreeNode leftChild;
        SegTreeNode rightChild;
        int sum;
        int leftIndex;
        int rightIndex;
        
        public SegTreeNode(int[] nums)
        {
            this(nums, 0, nums.length - 1);
        }
        
        public SegTreeNode(int[] nums, int left, int right)
        {
            this.leftIndex = left;
            this.rightIndex = right;
            if (left == right) {
                this.leftChild = null;
                this.rightChild = null;
                this.sum = nums[left];
            } else {
                int mid = (left + right) >>> 1;
                this.leftChild = new SegTreeNode(nums, left, mid);
                this.rightChild = new SegTreeNode(nums, mid + 1, right);
                this.sum = this.leftChild.sum + this.rightChild.sum;
            }
        }
        
        public void update(int index, int diffVal) {
            if (diffVal == 0) {
                return;
            }
            
            this.sum += diffVal;
            if (this.leftChild == null) {
                // In a segment tree either both or none of the children are null
                return;
            }
            if (index <= this.leftChild.rightIndex) {
                this.leftChild.update(index, diffVal);
            } else {
                this.rightChild.update(index, diffVal);
            }
        }
        
        public int sumRange(int left, int right) {
            boolean leftMatch = (this.leftIndex == left);
            boolean rightMatch = (this.rightIndex == right);
            if (leftMatch && rightMatch) {
                return this.sum;
            }
            if (right <= this.leftChild.rightIndex) {
                return this.leftChild.sumRange(left, right);
            }
            if (left >= this.rightChild.leftIndex) {
                return this.rightChild.sumRange(left, right);
            }
            return this.leftChild.sumRange(left, this.leftChild.rightIndex)
             + this.rightChild.sumRange(this.rightChild.leftIndex, right);
        }
    }
    
    public static class NumArray
    {
        SegTreeNode root;
        int[] nums;
        public NumArray(int[] nums)
        {
            this.nums = nums;
            if (this.nums == null || this.nums.length == 0) {
                this.root = null;
            } else {
                this.root = new SegTreeNode(nums);
            }
        }

        void update(int i, int val)
        {
            if (this.root == null || i < 0 || i >= nums.length) {
                return;
            }
            int diffVal = val - nums[i];
            root.update(i, diffVal);
            nums[i] = val;
        }

        public int sumRange(int i, int j) {
            if (i > j || i < 0 || j < 0
            || i >= nums.length || j >= nums.length)
            {
                return 0;
            }
            return root.sumRange(i, j);
        }
    }
}
