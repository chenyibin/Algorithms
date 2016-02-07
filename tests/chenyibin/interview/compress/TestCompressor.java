package chenyibin.interview.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class TestCompressor {

    Compressor testObj;

    @Test
    public void testCompressNoReduction()
    {
        byte[] buff = new byte[] {1,2,3,4,5,6,7,8,9};
        InputStream in = new ByteArrayInputStream(buff);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        testObj = new Compressor(in, out, 4, 4);

        writeOutput();

        byte[] outputBytes = out.toByteArray();
    }

    public boolean writeOutput()
    {
        try {
            testObj.writeToOutput();
        } catch (IOException e) {
            Assert.fail(e.getMessage());
            return false;
        }
        return true;
    }
}
