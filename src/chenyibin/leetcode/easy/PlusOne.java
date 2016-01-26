package chenyibin.leetcode.easy;

/**
 * Problem #66 on leetcode.com:
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 * @author Yibin Chen
 */
public class PlusOne
{
    public int[] plusOne(int[] digits)
    {
        boolean carry = true;
        for (int i = digits.length - 1; i >= 0; --i)
        {
            if (digits[i] == 9) {
                digits[i] = 0;
            } else {
                ++digits[i];
                carry = false;
                break;
            }
        }
        if (carry) {
            int newLength = digits.length + 1;
            int[] newDigits = new int[newLength];
            newDigits[0] = 1;
            int digitI = 0;
            for (int i = 1; i <= digits.length; ++i) {
                newDigits[i] = digits[digitI];
                digitI = i;
            }
            digits = newDigits;
        }
        return digits;
    }
}
