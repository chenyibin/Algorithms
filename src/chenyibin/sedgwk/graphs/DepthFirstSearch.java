package chenyibin.sedgwk.graphs;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class DepthFirstSearch
{
    private boolean[] marked;
    private int[] edgeTo;
    private int count;
    
    private GraphImpl graph;
    private int source;
    
    public DepthFirstSearch(GraphImpl g, int source)
    {
        this.graph = g;
        this.source = source;
        this.marked = new boolean[g.numVertices()];
        this.edgeTo = new int[g.numVertices()];
        this.count = 0;
    }
    
    public void depthFirstSearch()
    {
        this.graph.validateVertex(this.source);

        Deque<Integer> traversalStack = new LinkedList<Integer>();
        traversalStack.push(this.source);
        this.marked[this.source] = true;
        while (!traversalStack.isEmpty())
        {
            int visiting = traversalStack.pop();
            ++this.count;
            Deque<Integer> adjacents = this.graph.getAdjacents(visiting);
            Iterator<Integer> iter = adjacents.descendingIterator();
                
            while (iter.hasNext())
            {
                int adj = iter.next();
                if (!this.marked[adj]) {
                    this.edgeTo[adj] = visiting;
                    this.marked[adj] = true;
                    traversalStack.push(adj);
                }
            }
        }
    }
   
    public boolean hasPathTo(int vertex)
    {
        return this.marked[vertex];
    }

    public int getCount()
    {
        return this.count;
    }
    
    public Iterable<Integer> getPathTo(int vertex)
    {
        this.graph.validateVertex(vertex);
        if (!hasPathTo(vertex))
            return null;
        Deque<Integer> path = new LinkedList<Integer>();
        while (vertex != source)
        {
            path.push(vertex);
            vertex = this.edgeTo[vertex];
        }
        return path;
    }
}
