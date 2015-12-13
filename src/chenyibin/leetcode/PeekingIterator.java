package chenyibin.leetcode;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer>
{
    Iterator<Integer> iterator;

    Integer next = null;
    
    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (this.next == null && iterator.hasNext()) {
            this.next = this.iterator.next();
        }
        return this.next;
    }
    
    @Override
    public boolean hasNext() {
        if (this.next != null) {
            return true;
        }
        return this.iterator.hasNext();
    }

    @Override
    public Integer next() {
        if (this.next != null) {
            Integer returnNext = this.next;
            this.next = null;
            return returnNext;
        }
        return this.iterator.next();
    }

}
