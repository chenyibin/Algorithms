package chenyibin.leetcode;

public class MoveZeroes {

    public void moveZeroes(int[] nums)
    {
        int trailer = 0;
        for (int i = 0; i < nums.length; ++i)
        {
            if (nums[i] != 0) {
                nums[trailer] = nums[i];
                ++trailer;
            }
        }
        while (trailer < nums.length) {
            nums[trailer] = 0;
            ++trailer;
        }
    }
}
