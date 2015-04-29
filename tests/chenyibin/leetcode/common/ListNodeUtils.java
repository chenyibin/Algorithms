package chenyibin.leetcode.common;

public class ListNodeUtils {

	public static String stringify(ListNode node)
	{
		StringBuilder result = new StringBuilder();
		result.append('[');
		
		boolean first = true;
		while (node != null)
		{
			if (first) {
				first = false;
			} else {
				result.append(",");
			}
			result.append(node.val);
			node = node.next;
		}
		
		result.append(']');
		return result.toString();
	}
	
	public static boolean equals(ListNode n1, ListNode n2) {

		while (n1 != null && n2 != null)
		{
			if (n1.val != n2.val)
				return false;
			n1 = n1.next;
			n2 = n2.next;
		}
		
		if (n1 == null ^ n2 == null)
			return false;

		return true;
	}
	

}
