package chenyibin.leetcode;

import chenyibin.leetcode.common.ListNode;

/**
 * Problem #203 from leetcode.com:</br>
 * Remove all elements from a linked list of integers that have value val.</br>
 * Example:</br>
 * Given: 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6, val = 6</br>
 * Return: 1 -> 2 -> 3 -> 4 -> 5</br>
 * 
 * @author Yibin Chen
 */
public class RemoveLinkedListElements
{
    public ListNode removeElements(ListNode head, int val)
    {
        ListNode current = head;
        while (current != null && current.val == val)
        {
            current = current.next;
        }

        if (current == null) {
            return null;
        }
        ListNode result = current;
        
        ListNode next = current.next;
        while (next != null)
        {
            if (next.val == val) {
                // Remove value
                current.next = next.next;
            } else {
                current = next;
            }
            next = current.next;
        }
        return result;
    }
}
