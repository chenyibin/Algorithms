package chenyibin.leetcode;

import chenyibin.leetcode.common.ListNode;

/**
 * Problem #206 from leetcode.com:</br>
 * Reverse a linked list
 * 
 * @author Yibin Chen
 */
public class ReverseLinkedList {

	public ListNode reverseList(ListNode head) {
		ListNode oldList = head;
		ListNode newList = null;

		while (oldList != null)
		{
			ListNode removeFromOld = oldList;
			oldList = oldList.next;

			removeFromOld.next = newList;
			newList = removeFromOld;
		}

		return newList;
	}
}
