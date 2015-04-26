package chenyibin.leetcode;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

public class MergeKSortedLists {
	
	public static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; next = null; }
	}
	
	private static class ListNodeComparator implements Comparator<ListNode>
	{
		@Override
		public int compare(ListNode o1, ListNode o2)
		{
			if (o1 == o2) return 0;

			if (o1.val < o2.val) {
				return -1;
			} else if (o1.val > o2.val) {
				return 1;
			} else if (o1.hashCode() < o2.hashCode()) {
				// Use the default hashCode() to still
				// distinguish unique ListNode objects
				// even if their values are identical
				return -1;
			} else {
				return 1;
			}
		}
	}
	
	public ListNode mergeKLists(ListNode[] lists)
	{
		if (lists.length == 0)
			return null;
			
		NavigableSet<ListNode> priorityQueue = new TreeSet<ListNode>(
			new ListNodeComparator());

		for (ListNode node : lists) {
			if (node == null)
				continue;
			priorityQueue.add(node);
		}
		
		if (priorityQueue.isEmpty())
			return null;
			
		ListNode result = priorityQueue.pollFirst();
		ListNode current = result;
		
		if (result.next != null) {
			priorityQueue.add(result.next);
		}
		
		while (!priorityQueue.isEmpty()) {
			ListNode smallest = priorityQueue.pollFirst();
			if (smallest.next != null) {
				priorityQueue.add(smallest.next);
			}
			current.next = smallest;
			current = smallest;
		}
		return result;
	}
}
