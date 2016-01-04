package chenyibin.leetcode.hard;

import java.util.HashMap;
import java.util.Objects;

import chenyibin.leetcode.common.Point;

/**
 * Problem #149 on leetcode.com:
 * Given n points on a 2D plane, find the maximum number of points
 * that lie on the same straight line.
 * @author Yibin Chen
 */
public class MaxPointsOnLine {
    
    private static class Slope {
        int run, rise;
        boolean negative;
        
        public Slope(Point a, Point b)
        {
            this.run = b.y - a.y;
            this.rise = b.x - a.x;
            // use gcd to normalize slopes
            int gcd = getGCD(rise, run);
            if (gcd != 0) {
                this.run = this.run / gcd;
                this.rise = this.rise / gcd;
            }
            this.negative = (run < 0) ^ (rise < 0);
            this.run = Math.abs(this.run);
            this.rise = Math.abs(this.rise);
            if (run == 0) {
                this.rise = Integer.MAX_VALUE;
            } else if (rise == 0) {
                this.run = Integer.MAX_VALUE;
            }
        }
               
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Slope)) {
                return false;
            }
            Slope other = (Slope)o;
            return Objects.equals(this.run, other.run)
                && Objects.equals(this.rise, other.rise)
                && Objects.equals(this.negative, other.negative);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(this.run, this.rise, this.negative);
        }
    }

    public int maxPoints(Point[] points)
    {
        if (points.length < 2) {
            return points.length;
        }
        
        int max = 0;
        for (int i = 0; i < points.length; ++i)
        {
            Point start = points[i];
            HashMap<Slope, Integer> counts = new HashMap<>();
            int eqCount = 0;
            int slopeMax = 0;
            for (int j = i + 1; j < points.length; ++j)
            {
                Point other = points[j];
                if (start.x == other.x && start.y == other.y) {
                    // The other point is the same as the starting point
                    ++eqCount;
                } else {
                    // There is some slope from starting point to other point
                    Slope slope = new Slope(start, points[j]);
                    Integer count = counts.get(slope);
                    if (count == null) {
                        count = 0;
                    }
                    ++count;
                    // Count the number of points on the same slope
                    counts.put(slope, count);
                    slopeMax = Math.max(slopeMax, count);
                }
            }
            max = Math.max(max, 1 + eqCount + slopeMax);
        }
        return max;
    }
    
    private static int getGCD(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getGCD(b, a % b);
    }
}
