package chenyibin.leetcode.easy;

import chenyibin.leetcode.common.ListNode;

/**
 * Problem #234 on leetcode.com:
 * Given a singly linked list, determine if it is a palindrome.
 * @author Yibin Chen
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow.next = reverse(slow.next);
        slow = slow.next;
        while (slow != null) {
            if (slow.val != head.val) {
                return false;
            }
            slow = slow.next;
            head = head.next;
        }
        
        return true;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode reversed = null;
        
        while (head != null) {
            ListNode next = head.next;
            head.next = reversed;
            reversed = head;
            head = next;
        }
        return reversed;
    }
}
