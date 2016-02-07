package chenyibin.interview.compress;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Binary Writer for compressed output stream
 * @author Yibin Chen
 */
public class BinaryOutput
{
    private OutputStream outputStream;
    private int unwrittenBits;
    private int outputBuffer;

    private static int[] MASKS =
    {
        0x0, 0x1, 0x3, 0x7, 0xF,
        0x1F, 0x3F, 0x7F, 0xFF
    };

    public BinaryOutput(OutputStream outputStream)
    {
        this.outputStream = outputStream;
        this.outputBuffer = 0;
        this.unwrittenBits = 0;
    }

    /**
     * Write one bit.
     * @param out
     * @throws IOException
     */
    public void writeBoolean(boolean out) throws IOException
    {
        this.outputBuffer <<= 1;
        if (out)
        {
            this.outputBuffer |= 1;
        }

        if (this.unwrittenBits == 7)
        {
            this.outputStream.write(this.outputBuffer);
            this.unwrittenBits = 0;
        }
        else
        {
            ++this.unwrittenBits;
        }
    }

    /**
     * Write 16 bits.
     * @param offset
     * @throws IOException
     */
    public void writeOffset(int offset) throws IOException
    {
        writeByte(offset >> 8);
        writeByte(offset);
    }

    /**
     * Write 6 bits.
     * @param length
     * @throws IOException
     */
    public void writeLength(int out) throws IOException
    {
        if (this.unwrittenBits < 2)
        {
            this.outputBuffer <<= 6;
            this.outputBuffer |= out;
            this.unwrittenBits += 6;
        }
        else if (this.unwrittenBits == 2)
        {
            this.outputBuffer <<= 6;
            this.outputBuffer |= out;
            this.outputStream.write(this.outputBuffer);
            this.unwrittenBits = 0;
        }
        else
        {
            int numHiBits = 8 - this.unwrittenBits;
            this.outputBuffer <<= numHiBits;
            int outHigh = out >> numHiBits;
            this.outputBuffer |= outHigh;
            this.outputStream.write(this.outputBuffer);

            this.unwrittenBits = this.unwrittenBits - 2;
            this.outputBuffer = out & MASKS[this.unwrittenBits];
        }
    }

    /**
     * Write 8 bits.
     * @param out
     * @throws IOException
     */
    public void writeByte(int out) throws IOException
    {
        if (this.unwrittenBits == 0)
        {
            this.outputStream.write(out);
        }

        int numHiBits = 8 - this.unwrittenBits;
        this.outputBuffer <<= numHiBits;
        int outHigh = out >> numHiBits;
        this.outputBuffer |= outHigh;
        this.outputStream.write(this.outputBuffer);

        this.outputBuffer = out & MASKS[this.unwrittenBits];
    }

    /**
     * Flush the remaining output and close the stream.
     * @throws IOException
     */
    public void flushAndClose() throws IOException
    {
        if (this.unwrittenBits == 0)
        {
            return;
        }
        this.outputBuffer <<= (8 - this.unwrittenBits);

        // only the 8 lower bits are written out
        this.outputStream.write(this.outputBuffer);

        this.unwrittenBits = 0;

        this.outputStream.flush();
        this.outputStream.close();
    }

    @Override
    public String toString()
    {
        return Integer.toBinaryString(this.outputBuffer);
    }
}
