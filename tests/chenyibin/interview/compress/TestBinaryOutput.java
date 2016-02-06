package chenyibin.interview.compress;

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import chenyibin.interview.compress.BinaryOutput;

public class TestBinaryOutput {

    @Test
    public void testOutputBooleansOneByte()
    {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        BinaryOutput testObj = new BinaryOutput(byteOut);
        
        try {
            testObj.writeBoolean(true);
            testObj.writeBoolean(true);
            testObj.writeBoolean(true);
            testObj.writeBoolean(false);
            testObj.writeBoolean(false);
            testObj.writeBoolean(true);
            
            testObj.flushAndClose();
        } catch (IOException e) {
            fail("Unexpected IO Exception:" + e.getMessage());
        }
        
        byte[] output = byteOut.toByteArray();
        // Expecting 1110_0100
        byte[] expected = {(byte) 0xE4};
        Assert.assertArrayEquals(expected, output);
    }
    
    @Test
    public void testOutputBooleansTwoBytes()
    {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        BinaryOutput testObj = new BinaryOutput(byteOut);
        
        try {
            // Output 1110_0100
            testObj.writeBoolean(true);
            testObj.writeBoolean(true);
            testObj.writeBoolean(true);
            testObj.writeBoolean(false);
            testObj.writeBoolean(false);
            testObj.writeBoolean(true);
            testObj.writeBoolean(false);
            testObj.writeBoolean(false);
            
            // Output 0011_1000
            testObj.writeBoolean(false);
            testObj.writeBoolean(false);
            testObj.writeBoolean(true);
            testObj.writeBoolean(true);
            testObj.writeBoolean(true);

            testObj.flushAndClose();
        } catch (IOException e) {
            fail("Unexpected IO Exception:" + e.getMessage());
        }
        
        byte[] output = byteOut.toByteArray();
        // Expecting 1110_0100
        byte[] expected = {(byte) 0xE4, (byte) 0x38};
        Assert.assertArrayEquals(expected, output);
    }
        
    @Test
    public void testOutputByte()
    {
    }
}
