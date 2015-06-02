package chenyibin.sedgwk.graphs;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class TestDepthFirstSearch
{
    
    @Test
    public void testLineGraphDFS()
    {
        GraphImpl graph = new GraphImpl(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        
        DepthFirstSearch search = new DepthFirstSearch(graph, 4);
        search.depthFirstSearch();
        List<Integer> pathToZero = Lists.newArrayList(search.getPathTo(0));
        List<Integer> expectedPath = Lists.newArrayList();
        Collections.addAll(expectedPath, 3, 2, 1, 0);
        assertEquals(expectedPath, pathToZero);
    }

    @Test
    public void testLineGraphDFSFromMid()
    {
        GraphImpl graph = new GraphImpl(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        
        DepthFirstSearch search = new DepthFirstSearch(graph, 3);
        search.depthFirstSearch();
        List<Integer> pathToZero = Lists.newArrayList(search.getPathTo(0));
        List<Integer> expectedPath = Lists.newArrayList();
        Collections.addAll(expectedPath, 2, 1, 0);
        assertEquals(expectedPath, pathToZero);
        
        pathToZero = Lists.newArrayList(search.getPathTo(4));
        expectedPath.clear();
        Collections.addAll(expectedPath, 4);
        assertEquals(expectedPath, pathToZero);
    }
    
    @Test
    public void testDenseGraphDFS()
    {
        GraphImpl graph = new GraphImpl(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
 
        DepthFirstSearch search = new DepthFirstSearch(graph, 3);
        search.depthFirstSearch();
        List<Integer> pathToZero = Lists.newArrayList(search.getPathTo(2));
        List<Integer> expectedPath = Lists.newArrayList();
        Collections.addAll(expectedPath, 2);
        assertEquals(expectedPath, pathToZero);
    }
    
    @Test
    public void testSparseGraphWithCycleDFS()
    {
        GraphImpl graph = new GraphImpl(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);

        DepthFirstSearch search = new DepthFirstSearch(graph, 3);
        search.depthFirstSearch();
        List<Integer> pathToZero = Lists.newArrayList(search.getPathTo(1));
        List<Integer> expectedPath = Lists.newArrayList();
        Collections.addAll(expectedPath, 2, 0, 1);
        assertEquals(expectedPath, pathToZero);
    }
}