package chenyibin.leetcode;

public class EditDistance {

    public int minDistance(String word1, String word2)
    {
        if (word1.isEmpty()) {
            return word2.length();
        }
        if (word2.isEmpty()) {
            return word1.length();
        }
        
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        
        for (int row = 0; row <= word1.length(); ++row)
        {
            dp[row][0] = row;
        }
        for (int col = 1; col <= word2.length(); ++col)
        {
            dp[0][col] = col;
        }
        
        for (int row = 1; row <= word1.length(); ++row)
        {
            for (int col = 1; col <= word2.length(); ++col) {
                int rowMinus = row-1;
                int colMinus = col-1;
                int distByReplace = dp[rowMinus][colMinus];
                if (word1.charAt(rowMinus) != word2.charAt(colMinus)) {
                    ++distByReplace;
                }
                int distByAdd = dp[row][colMinus] + 1;
                int distByDel = dp[rowMinus][col] + 1;
                dp[row][col] = Math.min(distByReplace, Math.min(distByAdd, distByDel));
            }
        }
        
        return dp[word1.length()][word2.length()];
    }
}
