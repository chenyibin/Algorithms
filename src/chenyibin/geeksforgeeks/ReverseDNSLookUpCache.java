package chenyibin.geeksforgeeks;

public class ReverseDNSLookUpCache
{
	public static int NUM_CHARS = 11;
	private IpTrieNode top;
	
	private static class IpTrieNode
	{
		String URL;
		IpTrieNode[] nexts;
		
		IpTrieNode()
		{
			this.URL = null;
			nexts = new IpTrieNode[NUM_CHARS];
		}
		
		public IpTrieNode get(char c)
		{
			if (c == '.') {
				return createIfNull(10);
			} else {
				int num = Character.getNumericValue(c);
				return createIfNull(num);
			}
		}
	
		public IpTrieNode find(char c)
		{
			if (c == '.') {
				return this.nexts[10];
			} else {
				int num = Character.getNumericValue(c);
				return this.nexts[num];
			}
		}
			
		private IpTrieNode createIfNull(int c)
		{
			if (this.nexts[c] == null)
			{
				this.nexts[c] = new IpTrieNode();
				return this.nexts[c];
			}
			return this.nexts[c];
		}
	}
	

	public ReverseDNSLookUpCache()
	{
		this.top = new IpTrieNode();
	}
		
	public void insert(String ip, String URL)
	{
		
		IpTrieNode node = top;
		for (int i = 0; i < ip.length(); ++i)
		{
			char c = ip.charAt(i);
			node = node.get(c);
		}
		node.URL = URL;
	}
		
	public String find(String ip)
	{
		IpTrieNode traverse = top;
		for (int i = 0; i < ip.length(); ++i)
		{
			char c = ip.charAt(i);
			traverse = traverse.find(c);
			if (traverse == null) {
				return null;
			}
		}
		
		return traverse.URL;
	}
}
