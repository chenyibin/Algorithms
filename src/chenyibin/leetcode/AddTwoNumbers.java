package chenyibin.leetcode;

/**
 * Problem #2 on leetcode.com
 */
public class AddTwoNumbers
{
    public static class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode result = null;
        ListNode current = null;
        int carry = 0;
        do {
            // calculate new value
            ListNode more = null;
            if (l2 != null) {
                int sum = l1.val + l2.val + carry;
                l2 = l2.next;
                l1 = l1.next;

                more = new ListNode(sum % 10);
                carry = sum / 10;
            } else if (l1 != null) {
                int sum = l1.val + carry;
                l1 = l1.next;

                more = new ListNode(sum % 10);
                carry = sum / 10;
            } else {
                more = new ListNode(carry);
                carry = 0;
            }
            
            if (l1 == null) {
                l1 = l2;
                l2 = null;
            }
            
            if (result == null) {
                result = more;
            } else {
                current.next = more;
            }
            current = more;
        } while (l1 != null || carry != 0);
        return result;
    }
}
