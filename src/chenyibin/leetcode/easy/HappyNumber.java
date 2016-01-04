package chenyibin.leetcode.easy;

public class HappyNumber
{
    
    public boolean isHappy(int n)
    {
        int fast = next(next(n));
        int slow = next(n);
        while (fast != slow) {
            if (fast == 1) {
                return true;
            }
            fast = next(next(fast));
            slow = next(slow);
        }
        return (fast == 1);
    }
    
    public int next(int num) {
        int next = 0;
        while (num != 0) {
            int digit = num % 10;
            next += digit * digit;
            num /= 10;
        }
        return next;
    }
}
