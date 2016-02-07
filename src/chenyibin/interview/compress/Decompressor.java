package chenyibin.interview.compress;

import java.io.InputStream;
import java.io.OutputStream;

public class Decompressor
{
    static final int BUFFER_SIZE = Compressor.LOOKUP_BUFFER_SIZE;

    private BinaryInput binaryInput;
    private OutputStream outputStream;

    private CircularByteBuffer buffer;

    public Decompressor(InputStream compressedStream, OutputStream outputStream)
    {
        this(compressedStream, outputStream, BUFFER_SIZE);
    }

    Decompressor(InputStream compressedStream, OutputStream outputStream, int bufferSize)
    {
        this.binaryInput = new BinaryInput(compressedStream);
        this.outputStream = outputStream;

        this.buffer = new CircularByteBuffer(bufferSize);
    }
}
