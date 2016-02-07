package chenyibin.interview.compress;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Decompressor
{
    static final int BUFFER_SIZE = Compressor.LOOKUP_BUFFER_SIZE;

    private BinaryInput binaryInput;
    private InputStream inputStream;
    private OutputStream outputStream;

    private CircularByteBuffer buffer;

    public Decompressor(InputStream compressedStream, OutputStream outputStream)
    {
        this(compressedStream, outputStream, BUFFER_SIZE);
    }

    Decompressor(InputStream compressedStream, OutputStream outputStream, int bufferSize)
    {
        this.binaryInput = new BinaryInput(compressedStream);
        this.inputStream = compressedStream;
        this.outputStream = outputStream;

        this.buffer = new CircularByteBuffer(bufferSize);
    }

    public void writeToOutput() throws IOException
    {
        Boolean formatBit = binaryInput.readBoolean();
        while (formatBit != null)
        {
            if (formatBit == true)
            {
                int offset = binaryInput.readOffset();
                if (offset == BinaryInput.EOF) {
                    break;
                }
                int length = binaryInput.readLength();
                if (length == BinaryInput.EOF) {
                    break;
                }
                writeRangeFromBuffer(offset, length);
            }
            else
            {
                int newByte = binaryInput.readByte();
                if (newByte == BinaryInput.EOF) {
                    break;
                }
                writeOneByte(newByte);
            }
        }

        inputStream.close();
        outputStream.flush();
        outputStream.close();
    }

    private void writeRangeFromBuffer(int offset, int length) throws IOException
    {
        int bufferIndex = buffer.getEnd() - offset;

        for (int i = 0; i < length; ++i)
        {
            if (bufferIndex < 0) {
                bufferIndex += buffer.getCapacity();
            }
            byte newByte = buffer.getElement(bufferIndex);
            outputStream.write(newByte);
            buffer.addByte(newByte);
            ++bufferIndex;
        }

    }

    private void writeOneByte(int newByte) throws IOException
    {
        outputStream.write(newByte);
        buffer.addByte((byte) newByte);
    }
}
