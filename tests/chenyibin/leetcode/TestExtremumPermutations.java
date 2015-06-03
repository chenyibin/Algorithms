package chenyibin.leetcode;

import org.junit.Test;

public class TestExtremumPermutations {

	@Test
	public void testExtremum()
	{
		int N = 5;
		int[][] dp = new int[N+1][N+1];
		dp[1][1] = 1;
		
		for (int i = 2; i <= N; ++i)
		{
			int sum = 0;
			for (int j = 1; j <= i; ++j)
			{
				dp[i][j] += sum;
				sum += dp[i-1][j];
			}

			System.out.println("Mid:");
			printArray(dp);

			sum = 0;
			for (int j = i; j >= 1; --j)
			{
				dp[i][j] += sum;
				sum += dp[i-1][j-1];
			}
			System.out.println("Loop:");
			printArray(dp);
		}
		
		int ans = 0;
		for (int i = 1; i <= N; ++i)
		{
			ans += dp[N][i];
		}
		System.out.println(ans);
	}
	
	public void printArray(int[][] dp)
	{
		for (int i = 0; i < dp.length; ++i)
		{
			StringBuilder builder = new StringBuilder();
			boolean first = true;
			for (int j = 0; j < dp[i].length; ++j)
			{
				if (first) first = false;
				else builder.append(' ');
				builder.append(dp[i][j]);
			}
			System.out.println(builder.toString());
		}
		System.out.println();
	}
}
