package chenyibin.leetcode;

import chenyibin.leetcode.common.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * @author Yibin Chen
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head)
    {
        ListNode current = head;
        while (current != null) {
            while (current.next != null && current.val == current.next.val)
            {
                current.next = current.next.next;
            }
            current = current.next;
        }
        return head;
    }
}
