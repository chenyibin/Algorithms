package chenyibin.leetcode;

/**
 * Problem #5 on leetcode.com:
 * Given a string S, find the longest palindromic substring in S.
 * 
 * Solved using Manacher’s Algorithm.
 * 
 * @author Yibin Chen
 */
public class LongestPalindromicSubstring
{
    
    String preProcessed;
    
    public String longestPalindrome(String s)
    {
        preProcess(s);
        
        int center = 0;
        int rightFrontier = 0;
        int maxLength = 0;
        int maxIndex = -1;
        
        int[] palindromeLength = new int[preProcessed.length()];
        palindromeLength[0] = 0;
        for (int i = 1; i < preProcessed.length(); ++i) {
            
            int currentLength = 0;
            if (i < rightFrontier) {
                int mirroredIndex = center - (i - center);
                int mirroredLength = palindromeLength[mirroredIndex];
                int maxKnownLength = rightFrontier - i;
                currentLength = Math.min(mirroredLength, maxKnownLength);
            }
            
            int leftCheck = i - currentLength - 1;
            int rightCheck = i + currentLength + 1;
            while (leftCheck >= 0 && rightCheck < preProcessed.length()
                && preProcessed.charAt(leftCheck) == preProcessed.charAt(rightCheck))
            {
                ++currentLength;
                --leftCheck;
                ++rightCheck;
            }
            palindromeLength[i] = currentLength;
            
            if (currentLength > maxLength) {
                maxLength = currentLength;
                maxIndex = i;
            }
            int thisFrontier = i + currentLength;
            if (rightFrontier < thisFrontier) {
                rightFrontier = thisFrontier;
                center = i;
            }
        }
        
        int palindromeStart = (maxIndex - maxLength) / 2;
        int palindromeEnd = palindromeStart + maxLength;
        return s.substring(palindromeStart, palindromeEnd);
    }
    
    public void preProcess(String s)
    {
        StringBuilder builder = new StringBuilder();
        builder.append('\0');
        for (char c : s.toCharArray()) {
            builder.append(c);
            builder.append('\0');
        }
        this.preProcessed = builder.toString();
    }
}
