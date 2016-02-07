package chenyibin.interview.compress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import chenyibin.interview.lookup.LookupBuffer;
import chenyibin.interview.lookup.SuffixTreeLookupBuffer;

/**
 *
 * @author Yibin Chen
 */
public class Compressor
{
    private static final int MAX_REPLACE_LENGTH = 66;
    // 16-bit maximum offset = (2^16 - 1) = 65535
    private static final int LOOKUP_BUFFER_SIZE = 65535;

    private InputStream inputStream;

    private BinaryOutput binaryOutput;

    private CircularByteBuffer readBuffer;
    private LookupBuffer lookupBuffer;

    public Compressor(InputStream inputStream, OutputStream compressedStream)
    {
        this.inputStream = inputStream;
        this.binaryOutput = new BinaryOutput(compressedStream);

        this.readBuffer = new CircularByteBuffer(MAX_REPLACE_LENGTH);
        this.lookupBuffer = new SuffixTreeLookupBuffer(LOOKUP_BUFFER_SIZE);
    }

    public void createCompressedFile() throws IOException
    {
        int inputByte = inputStream.read();
        while (inputByte != -1)
        {
            readBuffer.addByte((byte) inputByte);
            if (readBuffer.isFull())
            {
                writeFromReadBuffer();
            }
            inputByte = inputStream.read();
        }

        while (!readBuffer.isEmpty()) {
            writeFromReadBuffer();
        }
        binaryOutput.flushAndClose();
    }

    private void writeFromReadBuffer() throws IOException
    {
        if (readBuffer.isEmpty()) {
            throw new RuntimeException("buffer empty");
        }
        int[] findPosition = lookupBuffer.findLongestMatch(
            readBuffer.getElements(),
            readBuffer.getStart(),
            readBuffer.getSize());

        if (findPosition == null || findPosition[1] < 3)
        {
            byte outputByte = readBuffer.remove();
            lookupBuffer.appendByte(outputByte);

            binaryOutput.writeBoolean(false);
            binaryOutput.writeByte(outputByte);
        }
        else
        {
            int offset = findPosition[0];
            int matchLength = findPosition[1];
            assert matchLength <= MAX_REPLACE_LENGTH;
            assert offset <= LOOKUP_BUFFER_SIZE;

            for (int i = 0; i < matchLength; ++i) {
                byte outputByte = readBuffer.remove();
                lookupBuffer.appendByte(outputByte);
            }

            int writeMatchLength = matchLength - 3;
            binaryOutput.writeBoolean(true);
            binaryOutput.writeOffset(offset);
            binaryOutput.writeLength(writeMatchLength);
        }
    }
}
