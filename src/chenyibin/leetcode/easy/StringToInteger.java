package chenyibin.leetcode.easy;

public class StringToInteger
{

    public int myAtoi(String str)
    {
        if (str == null) {
            return 0;
        }
        str = str.trim();
        if (str.isEmpty()) {
            return 0;
        }
        
        int start = 0;
        boolean negative = false;
        if (str.charAt(start) == '+') {
            start = 1;
        } else if (str.charAt(start) == '-') {
            start = 1;
            negative = true;
        }
        
        int result = 0;
        int beforeMax = Integer.MAX_VALUE / 10;
        int maxLastDigit = Integer.MAX_VALUE % 10;
        for (int i = start; i < str.length(); ++i) {
            int digit = str.charAt(i) - '0';
            if (digit < 0 || digit > 9) {
                break;
            }
            if (result > beforeMax || result == beforeMax && digit > maxLastDigit) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + digit;
        }
        return negative ? -result : result;
    }
}
