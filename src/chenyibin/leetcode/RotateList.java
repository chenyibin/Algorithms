package chenyibin.leetcode;

import chenyibin.leetcode.common.ListNode;

/**
 * Problem #61 on leetcode.com:
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * @author Yibin Chen
 */
public class RotateList
{
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        
        ListNode lead = head;
        for (int i = 0; i < k; ++i) {
            lead = lead.next;
            if (lead == null) {
                int listSize = i + 1;
                // Use mod to shorten the number of times
                // we use to traverse the list
                k = listSize + k % listSize;
                lead = head;
            }
        }
        ListNode trail = head;
        while (lead.next != null) {
            lead = lead.next;
            trail = trail.next;
        }
        
        lead.next = head;
        head = trail.next;
        trail.next = null;
        return head;
    }
}
