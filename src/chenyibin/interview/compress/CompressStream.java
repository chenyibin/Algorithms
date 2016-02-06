package chenyibin.interview.compress;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @author Yibin Chen
 */
public class CompressStream
{
    private InputStream inputStream;
    private OutputStream outputStream;
   
    
    public CompressStream(
        InputStream inputStream,
        OutputStream outputStream)
    {
        this.inputStream = inputStream;
        this.outputStream = outputStream;

    }
}
