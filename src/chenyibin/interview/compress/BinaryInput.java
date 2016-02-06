package chenyibin.interview.compress;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Yibin Chen
 */
public class BinaryInput
{
    final static int EOF = -1;
    final static int BYTE_MASK = 0xFF;
    final static int LENGTH_MASK = 0x3F;
    
    private InputStream inputStream;
    private int unreadBits;
    private int inputBuffer;
    
    public BinaryInput(InputStream inputStream)
    {
        this.inputStream = inputStream;
        ensureBuffer();
    }
    
    /**
     * Read the next single bit
     * @return
     */
    public boolean readBoolean()
    {
        if (eof())
        {
            throw new RuntimeException("EOF");
        }
        boolean bit = ((this.inputBuffer >> --this.unreadBits) & 1) == 1;
        if (this.unreadBits == 0)
        {
            ensureBuffer();
        }
        return bit;
    }
    
    /**
     * Read the next 8 bits. Return -1 if insufficient data.
     * @return
     */
    public int readByte()
    {
        if (eof()) {
            return EOF;
        }
        int tmpByte = this.inputBuffer;
        
        // simple case where we have exactly 8 bits
        if (this.unreadBits == 8) {
            ensureBuffer();
            return tmpByte;
        }
        
        tmpByte <<= (8 - this.unreadBits);
        ensureBuffer(this.unreadBits);
        if (eof()) {
            return EOF;
        }
        tmpByte |= this.inputBuffer >>> this.unreadBits;
        return tmpByte & BYTE_MASK;
    }
    
    /**
     * Read the next 16 bits. Return -1 if insufficient data.
     * @return
     */
    public int readOffset()
    {
        if (eof())
        {
            return EOF;
        }
        int firstByte = readByte();
        if (firstByte == EOF)
        {
            return EOF;
        }
        int secondByte = readByte();
        if (secondByte == EOF)
        {
            return EOF;
        }
        return (firstByte << 8) | secondByte;
    }
    
    /**
     * Read the next 6 bits. Return -1 if insufficient data.
     * @return
     */
    public int readLength()
    {
        if (eof())
        {
            return EOF;
        }
        int tmpByte = this.inputBuffer;
        if (this.unreadBits == 8)
        {
            this.unreadBits = 2;
            tmpByte >>= 2;
        }
        else if (this.unreadBits == 7)
        {
            this.unreadBits = 1;
            tmpByte >>= 1;
        }
        else if (this.unreadBits == 6)
        {
            ensureBuffer();
        }
        else
        {
            int moreBitsNeeded = 6 - this.unreadBits;
            tmpByte <<= moreBitsNeeded;
            ensureBuffer(8 - moreBitsNeeded);
            if (eof())
            {
                return EOF;
            }
            tmpByte |= this.inputBuffer >>> this.unreadBits;
        }
        return tmpByte & LENGTH_MASK;
    }
    
    public boolean eof()
    {
        return this.inputBuffer == EOF;
    }
    
    private void ensureBuffer()
    {
        ensureBuffer(8);
    }
    
    private void ensureBuffer(int newUnread)
    {
        try
        {
            this.inputBuffer = this.inputStream.read();
            this.unreadBits = newUnread;
        }
        catch (IOException e)
        {
            this.inputBuffer = -1;
            this.unreadBits = 0;
        }
    }

}
