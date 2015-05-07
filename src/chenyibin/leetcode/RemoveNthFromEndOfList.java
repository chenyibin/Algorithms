package chenyibin.leetcode;

import chenyibin.leetcode.common.ListNode;

/**
 * Problem #19 from leetcode.com:</br>
 * Given a linked list, remove the <i>n-th</i> node from
 * the end of list and return its head.
 * <p>
 * For example:
 * Given linked list: 1->2->3->4->5, and n = 2.</br>
 * Removing the second node from the end, the linked list becomes 1->2->3->5.
 * </p>
 * 
 * <strong>Solution:</strong></br>
 * Keep a <code>lead</code> and a <code>remove</code> reference.
 * The <code>remove</code> reference starts traversing the list from the beginning once
 * the head has made <i>n</i> steps. Once the head hits the end of the list
 * we'll remove the item pointed to by the <code>remove</code> reference.
 * 
 * @author Yibin Chen
 */
public class RemoveNthFromEndOfList
{
	public ListNode removeNthFromEnd(ListNode head, int n)
	{
		if (n < 1) {
			throw new IllegalArgumentException("n cannot be smaller than 1");
		}
		if (head == null) {
			return null;
		}

		int headStartCounter = 0;

		ListNode remove = null; 
		for (ListNode lead = head; lead != null; lead = lead.next)
		{
			if (headStartCounter < n) {
				++headStartCounter;
			} else if (remove == null) {
				remove = head;
			} else {
				remove = remove.next;
			}
		}

		if (headStartCounter < n) {
			throw new IllegalArgumentException("n is smaller than size of list");
		}

		if (remove == null) {
			// case where n is the size of the list
			return head.next;
		} else {
			remove.next = remove.next.next;
		}
		return head;
	}
}
