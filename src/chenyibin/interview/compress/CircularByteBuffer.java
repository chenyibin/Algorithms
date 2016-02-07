package chenyibin.interview.compress;

import java.util.NoSuchElementException;

/**
 * @author Yibin Chen
 */
public class CircularByteBuffer
{
    public static final int NO_REMOVE = -1;

    private byte[] elements;
    private int end;
    private int start;
    private int size;

    public CircularByteBuffer(int bufferSize)
    {
        this.elements = new byte[bufferSize];
        this.end = 0;
        this.start = 0;
        this.size = 0;
    }

    /**
     * Add a new byte to the buffer.
     * @param newByte
     * @return
     *      value of byte that was removed due to this addition
     *      or NO_REMOVE if nothing was removed.
     */
    public int addByte(byte newByte)
    {
        int removed = NO_REMOVE;
        if (isFull()) {
            removed = remove();
        }
        ++this.size;
        this.elements[this.end++] = newByte;
        if (this.end >= this.elements.length) {
            this.end = 0;
        }
        return removed;
    }

    public byte remove()
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        --this.size;
        byte result = this.elements[this.start++];
        if (this.start >= this.elements.length) {
            this.start = 0;
        }
        return result;
    }

    public void removeElements(int numRemove)
    {
        if (numRemove > this.size) {
            throw new RuntimeException("not enough elements to remove");
        }
        this.start += this.size;
        if (this.start >= this.elements.length) {
            this.start -= this.elements.length;
        }
        this.size -= numRemove;
    }

    public boolean isEmpty()
    {
        return this.size == 0;
    }

    public boolean isFull()
    {
        return this.size == getCapacity();
    }

    public int getEnd()
    {
        return this.end;
    }

    public int getStart()
    {
        return this.start;
    }

    public int getSize()
    {
        return this.size;
    }

    public int getCapacity()
    {
        return this.elements.length;
    }

    public byte[] getElements()
    {
        return this.elements;
    }

    public byte getElement(int index)
    {
        int effectiveIndex = index % elements.length;
        if (!inBuffer(effectiveIndex)) {
            throw new NoSuchElementException();
        }
        return this.elements[effectiveIndex];
    }

    public boolean inBuffer(int index)
    {
        if (isEmpty()) {
            return false;
        }
        if (start < end) {
            return (index >= start && index < end);
        }
        return (index >= start || index < end);
    }
}
