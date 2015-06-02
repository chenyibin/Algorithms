package chenyibin.sedgwk.graphs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class GraphImpl {
    
    private int vertices;
    private int edges;
    private List<Deque<Integer>> adjacency;

    public GraphImpl(final int vertices)
    {
        this.vertices = vertices;
        this.edges = 0;
        this.adjacency = new ArrayList<Deque<Integer>>(this.vertices);
        
        for (int i = 0; i < this.numVertices(); ++i)
        {
            this.adjacency.add(new LinkedList<Integer>());
        }
    }

    public int numEdges() {
        return edges;
    }

    public int numVertices() {
        return vertices;
    }
    
    public void validateVertex(int vertex) {
        if (vertex < 0 || vertex >= this.vertices)
            throw new IndexOutOfBoundsException("vertex " + vertex + "out of bounds");
    }
    
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        this.adjacency.get(v).add(w);
        this.adjacency.get(w).add(v);
        ++this.edges;
    }
    
    public Deque<Integer> getAdjacents(int vertex) {
        validateVertex(vertex);
        return this.adjacency.get(vertex);
    }
    
    public int getDegree(int vertex) {
        validateVertex(vertex);
        return this.adjacency.size();
    }
}
