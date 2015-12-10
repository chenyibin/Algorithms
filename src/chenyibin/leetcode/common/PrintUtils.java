package chenyibin.leetcode.common;

public class PrintUtils {

    public static String intArrayToString(int[] result)
    {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        builder.append('[');
        for (int i : result) {
            if (first) first = false;
            else builder.append(',');
            builder.append(Integer.toString(i));
        }
        builder.append(']');
        return builder.toString();
    }
}
