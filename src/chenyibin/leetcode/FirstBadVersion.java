package chenyibin.leetcode;

public class FirstBadVersion
{
    int badVersion;
    
    public int firstBadVersion(int n)
    {
        int start = 1;
        int end = n;
        
        while (start < end) {
            int mid = start + (end-start) / 2;
            if (isBadVersion(mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private boolean isBadVersion(int ver) {
        return ver >= badVersion;
    }
}
