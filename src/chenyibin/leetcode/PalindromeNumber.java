package chenyibin.leetcode;

/**
 * Problem #9 on leetcode.com:
 * Determine whether an integer is a palindrome.
 * 
 * @author Yibin Chen
 */
public class PalindromeNumber
{
    public boolean isPalindrome(int x)
    {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int reverse = 0;
        while (x > reverse){
            int leastSigDigit = x % 10;
            reverse = reverse * 10 + leastSigDigit;
            x = x / 10;
        }
        return (x == reverse|| x == reverse / 10);
    }
}
