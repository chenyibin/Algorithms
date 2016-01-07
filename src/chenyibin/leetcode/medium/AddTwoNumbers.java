package chenyibin.leetcode.medium;

import chenyibin.leetcode.common.ListNode;

/**
 * Problem #2 on leetcode.com:
 * You are given two linked lists representing two non-negative numbers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * 
 * @author Yibin Chen
 */
public class AddTwoNumbers
{
	public ListNode addTwoNumbers(ListNode l1, ListNode l2)
	{
		ListNode result = null;
		ListNode currentResultNode = null;
		int carry = 0;
		do {
			// calculate new value
			ListNode nextResultNode = null;
			if (l2 != null) {
				int sum = l1.val + l2.val + carry;
				l2 = l2.next;
				l1 = l1.next;

				nextResultNode = new ListNode(sum % 10);
				carry = sum / 10;
			} else if (l1 != null) {
				int sum = l1.val + carry;
				l1 = l1.next;

				nextResultNode = new ListNode(sum % 10);
				carry = sum / 10;
			} else {
				nextResultNode = new ListNode(carry);
				carry = 0;
			}
			
			// let l2 become null first
			if (l1 == null) {
				l1 = l2;
				l2 = null;
			}

			if (result == null) {
				result = nextResultNode;
			} else {
				currentResultNode.next = nextResultNode;
			}
			currentResultNode = nextResultNode;
		} while (l1 != null || carry != 0);
		return result;
	}
}
