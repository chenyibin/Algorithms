package chenyibin.interview.lookup;

/**
 * Lookup Buffer to find the longest match
 * @author Yibin Chen
 */
public interface LookupBuffer
{
    public void appendByte(byte newByte);

    /**
     * @param lookup
     * @return
     */
    public int[] findLongestMatch(byte[] lookup);

    /**
     * @param lookup array to find
     * @param start start of circular buffer
     * @param length max length of match
     * @return
     * <li>Return pair of offset from front and match length given search array.</li>
     * <li>Return null if no match was found</li>
     */
    public int[] findLongestMatch(byte[] lookup, int start, int length);
}
