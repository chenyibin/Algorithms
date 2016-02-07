package chenyibin.interview.lookup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import chenyibin.interview.compress.CircularByteBuffer;

/**
 * @author Yibin Chen
 */
public class HashLookupBuffer implements LookupBuffer
{
    private final int NO_PREV = -1;
    
    private CircularByteBuffer byteBuffer;
    private Map<Integer,Set<Integer>> lookup;
    private int prevByte;
    
    public HashLookupBuffer()
    {
        super();
        this.lookup = new HashMap<>();
        this.prevByte = NO_PREV;
    }
    
    @Override
    public void appendByte(byte newByte)
    {
        int removed = this.byteBuffer.addByte(newByte);
        if (removed == CircularByteBuffer.NO_REMOVE) {
            return;
        }
        
        if (this.prevByte != NO_PREV)
        {
            int doubleByte = (this.prevByte <<= 8) | newByte;
            Set<Integer> indexSet = lookup.get(doubleByte);
            if (indexSet == null) {
                indexSet = new HashSet<>();
                lookup.put(doubleByte, indexSet);
            }
            List<Integer> removeIndex = new ArrayList<>();
            for (int index : indexSet) {
                if (!this.byteBuffer.inBuffer(index)) {
                    removeIndex.add(index);
                }
            }
        }
        this.prevByte = newByte;
    }

    @Override
    public int[] findLongestMatch(byte[] lookup) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] findLongestMatch(byte[] lookup, int start, int length) {
        // TODO Auto-generated method stub
        return null;
    }

}
