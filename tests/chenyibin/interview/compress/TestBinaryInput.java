package chenyibin.interview.compress;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import chenyibin.interview.compress.BinaryInput;

public class TestBinaryInput
{
    
    @Test
    public void testReadBytes()
    {
        byte[] buff = {(byte) 0xFF, (byte) 0x00, (byte) 0xF0};
        InputStream stream = new ByteArrayInputStream(buff);
        BinaryInput testObj = new BinaryInput(stream);
        
        // Reading 0xFF
        Assert.assertEquals(255, testObj.readByte());
        // Reading 0x00
        Assert.assertEquals(0, testObj.readByte());
        // Reading 0xF0
        Assert.assertEquals(240, testObj.readByte());

        Assert.assertEquals(BinaryInput.EOF, testObj.readByte());
    }
    
    @Test
    public void testReadBooleanAndBytes()
    {
        byte[] buff = {(byte) 0xFF, (byte) 0x00, (byte) 0xF0};
        InputStream stream = new ByteArrayInputStream(buff);
        BinaryInput testObj = new BinaryInput(stream);

        Assert.assertEquals(true, testObj.readBoolean());
        Assert.assertEquals(true, testObj.readBoolean());

        // Reading 1111_1100
        Assert.assertEquals(0xFC, testObj.readByte());
        // Reading 0000_0011
        Assert.assertEquals(0x03, testObj.readByte());

        Assert.assertEquals(true, testObj.readBoolean());
        Assert.assertEquals(true, testObj.readBoolean());
        Assert.assertEquals(false, testObj.readBoolean());

        Assert.assertEquals(BinaryInput.EOF, testObj.readByte());
    }

    @Test
    public void testReadLengthJustRight()
    {
        byte[] buff = {(byte) 0xFF, (byte) 0x00, (byte) 0xF0};
        InputStream stream = new ByteArrayInputStream(buff);
        BinaryInput testObj = new BinaryInput(stream);
        
        // Reading 11_1111
        Assert.assertEquals(0x3F, testObj.readLength());
        // Reading 11_0000
        Assert.assertEquals(0x30, testObj.readLength());
        // Reading 00_0011
        Assert.assertEquals(0x03, testObj.readLength());
        // Reading 11_0000
        Assert.assertEquals(0x30, testObj.readLength());

        Assert.assertEquals(BinaryInput.EOF, testObj.readLength());
    }
    
    @Test
    public void testReadLengthOneOff()
    {
        byte[] buff = {(byte) 0xFF, (byte) 0x00, (byte) 0xF0};
        InputStream stream = new ByteArrayInputStream(buff);
        BinaryInput testObj = new BinaryInput(stream);
        
        Assert.assertEquals(true, testObj.readBoolean());

        // Reading 11_1111
        Assert.assertEquals(0x3F, testObj.readLength());
        // Reading 10_0000
        Assert.assertEquals(0x20, testObj.readLength());
        // Reading 00_0111
        Assert.assertEquals(0x07, testObj.readLength());

        Assert.assertEquals(BinaryInput.EOF, testObj.readLength());
    }
    
     
    @Test
    public void testReadLengthTwoOff()
    {
        byte[] buff = {(byte) 0xFF, (byte) 0x00, (byte) 0xF0};
        InputStream stream = new ByteArrayInputStream(buff);
        BinaryInput testObj = new BinaryInput(stream);
        
        Assert.assertEquals(true, testObj.readBoolean());
        Assert.assertEquals(true, testObj.readBoolean());

        // Reading 11_1111
        Assert.assertEquals(0x3F, testObj.readLength());
        // Reading 00_0000
        Assert.assertEquals(0x00, testObj.readLength());
        // Reading 00_1111
        Assert.assertEquals(0x0F, testObj.readLength());

        Assert.assertEquals(BinaryInput.EOF, testObj.readLength());
    }
}
