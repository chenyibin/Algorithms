package chenyibin.sedgwk.graphs;

import java.util.Deque;
import java.util.LinkedList;

public class BreathFirstSearch
{
    private boolean[] marked;
    private int[] edgeTo;
    private int count;

    private GraphImpl graph;
    private int source;
    
    public BreathFirstSearch(GraphImpl g, int source)
    {
        this.graph = g;
        this.source = source;
        this.marked = new boolean[g.numVertices()];
        this.edgeTo = new int[g.numVertices()];
        this.count = 0;
    }
    
    public void breathFirstSearch()
    {
        this.graph.validateVertex(source);
        Deque<Integer> traversalQueue = new LinkedList<Integer>();
        traversalQueue.offer(source);
        this.marked[source] = true;
        while (!traversalQueue.isEmpty())
        {
            int visiting = traversalQueue.poll();
            ++this.count;
            for (Integer adj :this.graph.getAdjacents(visiting))
            {
                if (!this.marked[adj]) {
                    this.marked[adj] = true;
                    this.edgeTo[adj] = visiting;
                    traversalQueue.offer(adj);
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
