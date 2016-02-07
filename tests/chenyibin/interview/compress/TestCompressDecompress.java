package chenyibin.interview.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Test;

public class TestCompressDecompress
{
    Compressor compressor;
    Decompressor decompressor;

    @Test
    public void testXmlDataCompressDecompress() throws IOException
    {
        File inputFile = new File("testdata/compress/badges.xml");
        InputStream inputStream = new BufferedInputStream(new FileInputStream(inputFile));

        File compressedFile = new File("badges.cbytes");
        OutputStream compressedOutput = new BufferedOutputStream(new FileOutputStream(compressedFile));
        compressor = new Compressor(inputStream, compressedOutput, 8, 16);

        compressor.writeToOutput();
    }
}
