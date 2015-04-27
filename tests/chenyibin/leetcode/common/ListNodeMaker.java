package chenyibin.leetcode.common;

public class ListNodeMaker
{
	public static ListNode make(int... elements)
	{
		ListNode head = new ListNode(elements[0]);
		
		ListNode current = head;
		boolean first = true;
		for (int element : elements)
		{
			if (first)
			{
				// skip first one since we already got it
				first = false;
			}
			else
			{
				current.next = new ListNode(element);
				current = current.next;
			}
		}
		
		return head;
	}
}
