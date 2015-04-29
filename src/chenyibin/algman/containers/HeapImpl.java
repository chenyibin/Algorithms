package chenyibin.algman.containers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import chenyibin.algman.exceptions.EmptyHeapException;

public class HeapImpl<T extends Comparable<T>> {

	List<T> items;
	Comparator<T> compare;
	
	public HeapImpl()
	{
		items = new ArrayList<T>();
		this.compare = (o1, o2) -> o1.compareTo(o2);
	}
	
	public HeapImpl(List<T> contents)
	{
		this.items = new ArrayList<T>(contents);
		for (int i = this.items.size() - 1; i >= 0; --i)
		{
			bubbleDown(i);
		}
	}
	
	public T getMin()
	{
		if (items.isEmpty()) {
			throw new EmptyHeapException();
		}
		return items.get(0);
	}
	
	/**
	 * Remove minimum from heap.
	 * Takes O(log(n)) time.
	 */
	public T extractMin()
	{
		if (items.isEmpty()) {
			throw new EmptyHeapException();
		}
		
		T min = items.get(0);
		int lastIndex = items.size() - 1;
		// Move last item into first position
		items.set(0, items.get(lastIndex));
		items.remove(lastIndex);
		
		bubbleDown(0);
		return min;
	}
	
	public void insert(T newItem)
	{
		int newItemIndex = items.size();
		items.add(newItem);
		bubbleUp(newItemIndex);
	}
	
	public int size()
	{
		return this.items.size();
	}
	
	/**
	 * Returns a sorted list of items in the heap
	 * @return sorted list
	 */
	public List<T> heapSort()
	{
		List<T> itemsCopy = new ArrayList<T>(this.items);
		List<T> result = new ArrayList<T>(this.items.size());
		
		while (size() > 0) {
			result.add(extractMin());
		}
		
		this.items = itemsCopy;
		return result;
	}
	
	private void bubbleDown(int n)
	{
		// Start out with the left child as our candidate parent
		int leftIndex = getLeftChildIndex(n);
		if (leftIndex == -1) {
			return;
		}
		int candidateParentIndex = leftIndex;
		T candidateParent = items.get(candidateParentIndex);
		
		// Pick the right child if its a better parent
		int rightIndex = leftToRightChildIndex(leftIndex);
		if (rightIndex != -1) {
			T right = items.get(rightIndex);
			if (correctParentChild(right, candidateParent)) {
				candidateParentIndex = rightIndex;
				candidateParent = items.get(candidateParentIndex);
			}
		}
		
		// Compare the best candidate parent with the current parent
		// If we swap, do a bubble down on index of item we just swapped
		T currentParent = items.get(n);
		if (correctParentChild(candidateParent, currentParent)) {
			items.set(n, candidateParent);
			items.set(candidateParentIndex, currentParent);
			bubbleDown(candidateParentIndex);
		}
	}

	private void bubbleUp(int n)
	{
		int parentIndex = getParentIndex(n);
		if (parentIndex == -1)
			return;
		
		T current = items.get(n);
		T parent  = items.get(parentIndex);
		if (!correctParentChild(parent, current)) {
			items.set(n, parent);
			items.set(parentIndex, current);
			bubbleUp(parentIndex);
		}
	}
	
	/**
	 * Strangely named function to specify comparator between parent and child.
	 * @param parent
	 * @param child
	 * @return true if parent-child relationship is accurate
	 */
	protected boolean correctParentChild(T parent, T child)
	{
		return this.compare.compare(parent, child) < 0;
	}
	
	private int getParentIndex(int n)
	{
		if (n == 0) return -1;
		else return (n-1)/2;
	}
	
	private int getLeftChildIndex(int n)
	{
		int left = (n + 1) * 2 - 1;
		if (left >= items.size())
			return -1;
		return left;
	}
	
	private int getRightChildIndex(int n)
	{
		int right = (n + 1) * 2;
		if (right >= items.size()) {
			return -1;
		}
		return right;
	}
	
	private int leftToRightChildIndex(int left)
	{
		int right = left + 1;
		if (right >= items.size()) {
			return -1;
		}
		return right;
	}
	
	@Override
	public String toString()
	{
		boolean first = true;
		StringBuilder builder = new StringBuilder();
		for (T item : items) 
		{
			if (first) {
				first = false;
			} else {
				builder.append(" ");
			}
			builder.append(item.toString());
		}
		return builder.toString();
	}
	
	boolean verify()
	{
		Stack<Integer> traversalStack = new Stack<Integer>();
		traversalStack.push(0);
		while (!traversalStack.isEmpty())
		{
			int currentIndex = traversalStack.pop();
			int leftIndex    = getLeftChildIndex(currentIndex);
			int rightIndex   = getRightChildIndex(currentIndex);
			
			T current = items.get(currentIndex);
			if (leftIndex != -1) {
				T left = items.get(leftIndex);
				if (!correctParentChild(current, left))
					return false;
				traversalStack.push(leftIndex);
			}
			if (rightIndex != -1) {
				T right = items.get(rightIndex);
				if (!correctParentChild(current, right))
					return false;
				traversalStack.push(rightIndex);
			}
		}
		return true;
	}
}
