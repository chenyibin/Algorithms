package chenyibin.leetcode;

import chenyibin.leetcode.common.ListNode;

/**
 * Problem #21 on leetcode.com</br>
 * Merge two sorted linked lists
 * 
 * @author Yibin Chen
 */
public class MergeTwoSortedLists
{
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		ListNode mergedHead = null;

		if (l1.val > l2.val) {
			mergedHead = l2;
			l2 = l2.next;
		} else {
			mergedHead = l1;
			l1 = l1.next;
		}

		ListNode current = mergedHead;

		while (l1 != null && l2 != null)
		{
			if (l1.val > l2.val) {
				current.next = l2;
				l2 = l2.next;
			} else {
				current.next = l1;
				l1 = l1.next;
			}
			current = current.next;
		}
		if (l1 == null)
		{
			current.next = l2;
		}
		if (l2 == null)
		{
			current.next = l1;
		}

		return mergedHead;
	}
}
