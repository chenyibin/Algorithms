package chenyibin.geeksforgeeks;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class TestTopologicalSort {

    @Test
    public void testSixNodeGraph()
    {
        AdjListGraph graph = new AdjListGraph(6);
        graph.addEdge(5,2)
             .addEdge(5,0)
             .addEdge(4,0)
             .addEdge(4,1)
             .addEdge(2,3)
             .addEdge(3,1);
        
        TopologicalSort sorter = new TopologicalSort(graph);
        List<Integer> sorted = sorter.getTopologicalSort();
        
        List<Integer> expected = Lists.newArrayList(5,4,2,3,1,0);
        Assert.assertEquals(expected, sorted);
    }

    @Test
    public void testTenNodeGraph()
    {
        AdjListGraph graph = new AdjListGraph(9);
        graph.addEdge(8,7)
             .addEdge(3,0)
             .addEdge(1,0)
             .addEdge(1,3)
             .addEdge(2,3)
             .addEdge(1,2)
             .addEdge(1,4)
             .addEdge(4,2)
             .addEdge(1,7)
             .addEdge(5,7);
             
        TopologicalSort sorter = new TopologicalSort(graph);
        List<Integer> sorted = sorter.getTopologicalSort();

        List<Integer> expected = Lists.newArrayList(8,6,5,1,4,2,3,7,0);
        Assert.assertEquals(expected, sorted);
    }
}
