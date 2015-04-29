package chenyibin.leetcode.common;

public class CombinationGenerator {
	int ni;
	int ki;
	int[] combo;

	public CombinationGenerator(int n, int k)
	{
		this.ni = n - 1;
		this.ki = k - 1;
		this.combo = new int[k];
		for (int i = 0; i < k; ++i)
		{
			combo[i] = i;
		}
	}

	int get(int i) {
		return combo[i];
	}

	public boolean increment() {

		int incremented = Integer.MIN_VALUE;
		for (int i = ki; i >= 0; --i)
		{
			if (combo[i] == ni) {
				continue;
			}
			if (i != ki && (combo[i] == combo[i+1] - 1))  {
				continue;
			}
			++combo[i];
			incremented = i;
			break;
		}

		if (incremented == Integer.MIN_VALUE)
		{
			return false;
		}
		else
		{
			// Reset everything after the index we just incremented
			for (int j = incremented + 1; j < combo.length; ++j)
			{
				combo[j] = combo[j-1] + 1;
			}
		}
		return true;
	}
}
