package chenyibin.interview.compress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Yibin Chen
 */
public class HashLookupBuffer extends LookupBuffer
{
    private final int NO_PREV = -1;
    
    Map<Integer,Set<Integer>> lookup;
    int prevByte;
    
    public HashLookupBuffer()
    {
        super();
        this.lookup = new HashMap<>();
        this.prevByte = NO_PREV;
    }
    
    public void addByte(byte newByte)
    {
        int removed = this.byteBuffer.addByte(newByte);
        if (removed != CircularByteBuffer.NO_REMOVE) {
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
    public Optional<int[]> lookup(CircularByteBuffer lookup)
    {
        return Optional.empty();
    }

}
