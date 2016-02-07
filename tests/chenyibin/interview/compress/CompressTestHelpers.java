package chenyibin.interview.compress;

import java.util.ArrayList;
import java.util.List;

public class CompressTestHelpers
{
    static class CheckElement
    {
        boolean isLookup;
        int oneByte;
        int lookupOffset;
        int lookupLength;
        
        CheckElement(int oneByte)
        {
            this.isLookup = false;
            this.oneByte = oneByte;
        }
        
        CheckElement(int offset, int length)
        {
            this.isLookup = true;
            this.lookupOffset = offset;
            this.lookupLength = length;
        }
    }
    
    public static class CompressorChecker
    {
        List<CheckElement> expected;
        
        public CompressorChecker()
        {
            this.expected = new ArrayList<>();
        }
        
        public void addByte(int oneByte)
        {
            CheckElement newCheck = new CheckElement(oneByte);
            this.expected.add(newCheck);
        }
        
        public void addLookup(int offset, int length)
        {
            CheckElement newCheck = new CheckElement(offset, length);
            this.expected.add(newCheck);
        }
        
        boolean checkBytes(byte[] bytes)
        {
            return true;
        }
    }
}
