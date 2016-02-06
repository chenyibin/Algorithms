package chenyibin.interview.compress;

import java.util.Optional;

public abstract class LookupBuffer
{
    // Read buffer of size 64kb
    public static final int BUFFER_SIZE = 65535;
    
    protected CircularByteBuffer byteBuffer;
    
    public LookupBuffer()
    {
        this.byteBuffer = new CircularByteBuffer(BUFFER_SIZE);
    }
    
    abstract public Optional<int[]> lookup(CircularByteBuffer lookup);
}
