package chenyibin.leetcode;

import chenyibin.leetcode.common.ListNode;

public class ReverseNodeInKGroup
{
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null)
			return null;

		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		ListNode prev = fakeHead;
		ListNode advanced = advance(fakeHead, k);

		while (advanced != null) {
			prev = reverse(prev, advanced);
			advanced = advance(prev, k);
		}

		return fakeHead.next;
	}

	private ListNode reverse(ListNode prev, ListNode advanced) {
		ListNode next = advanced.next;

		advanced.next = null;
		ListNode nextPrev = prev.next;
		ListNode oldList = prev.next;
		ListNode newList = next;

		while (oldList != null) {
			ListNode transfer = oldList;
			oldList = oldList.next;
			transfer.next = newList;
			newList = transfer;
		}

		prev.next = newList;
		return nextPrev;
	}

	private ListNode advance(ListNode node, int k) {
		for (int i = 0; i < k && node != null; ++i) {
			node = node.next;
		}
		return node;
	}
}
