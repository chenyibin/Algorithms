package chenyibin.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Problem #71 on leetcode.com:
 * Given an absolute path for a file (Unix-style), simplify it.
 * @author Yibin Chen
 */
public class SimplifyPath {

    public String simplifyPath(String path)
    {
        String[] pathSegs = path.split("/");
        
        Deque<String> simplified = new LinkedList<>();
        for (String pathSeg : pathSegs) {
            if ("..".equals(pathSeg)) {
                simplified.pollLast();
            } else if (!pathSeg.isEmpty() && !(".".equals(pathSeg))) {
                simplified.addLast(pathSeg);
            }
        }
        StringBuilder result = new StringBuilder();
        simplified.forEach((seg) -> result.append('/').append(seg));
        return (result.length() == 0) ? "/" : result.toString();
    }
}
