package chenyibin.leetcode.hard;

import org.junit.Assert;
import org.junit.Test;

import chenyibin.leetcode.common.Point;

public class TestMaxPointsOnLine {

    @Test
    public void testSlope() {
        Point[] points = {new Point(0,0), new Point(-1,-1), new Point(2,2)};
        MaxPointsOnLine solver = new MaxPointsOnLine();
        Assert.assertEquals(3, solver.maxPoints(points));
    }
}
