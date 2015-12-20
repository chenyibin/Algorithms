package chenyibin.leetcode;

import chenyibin.leetcode.common.ListNode;

/**
 * Problem #160 on leetcode.com:
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 * @author Yibin Chen
 */
public class IntersectionTwoLinkedLists
{
    /**
     * A smarter solution 
     * @param headA
     * @param headB
     * @return
     */
    public ListNode solutionNoCounter(ListNode headA, ListNode headB)
    {
        ListNode aList = headA;
        ListNode bList = headB;
        
        while (aList != bList)
        {
            aList = (aList == null) ?
                headB : aList.next;
            bList = (bList == null) ?
                headA : bList.next;
        }
        return aList;
    }

    /**
     * Solution which gets the length of each list first
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB)
    {
        
        int aLen = getLength(headA);
        int bLen = getLength(headB);
        
        ListNode firstNode = null;
        ListNode secondNode = null;
        int waitTime = Math.abs(aLen - bLen);
        if (aLen > bLen) {
            firstNode = headA;
            secondNode = headB;
        }
        else {
            firstNode = headB;
            secondNode = headA;
        }
        
        while (firstNode != null && firstNode != secondNode) {
            if (waitTime > 0) {
                --waitTime;
            } else {
                secondNode = secondNode.next;
            }
            firstNode = firstNode.next;
        }
        
        return firstNode;
    }

    private int getLength(ListNode head) {
        
        int len = 0;
        while (head != null) {
            ++len;
            head = head.next;
        }
        return len;
    }
   }
