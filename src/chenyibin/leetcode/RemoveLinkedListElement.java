package chenyibin.leetcode;

public class RemoveLinkedListElement {
    
    public class ListNode
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    
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
