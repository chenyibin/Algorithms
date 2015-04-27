package chenyibin.leetcode;

import chenyibin.leetcode.common.ListNode;

/**
 * Problem #24 from leetcode.com:
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * @author Yibin Chen
 */
public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head)
    {
    	if (head == null)
    		return null;
    		
    	ListNode current = head;
    	head = swap(head);
    	
    	while (current != null && current.next != null)
    	{
    		current.next = swap(current.next);
    		current = current.next.next;
    	}
    	
    	return head;
    }
    
    private ListNode swap(ListNode first) {
    	ListNode second = first.next;
    	
    	if (second == null)
    		return first;
    	
    	first.next = second.next;
    	second.next = first;
    	return second;
    }
}
