package chenyibin.geeksforgeeks;

import java.util.LinkedList;
import java.util.List;

public class AdjListGraph {

    private int numVertices;
    private List<Integer>[] adjacenyLists;

    @SuppressWarnings("unchecked")
    public AdjListGraph(int numVertices)
    {
        this.numVertices = numVertices;
        this.adjacenyLists = new LinkedList[numVertices];
        
        for (int i = 0; i < numVertices; ++i) {
            this.adjacenyLists[i] = new LinkedList<>();
        }
    }
    
    public AdjListGraph addEdge(int from, int to)
    {
        this.adjacenyLists[from].add(to);
        return this;
    }

    public int getNumVertices() {
        return numVertices;
    }
    
    public List<Integer> getConnectedTo(int vertex) {
        return this.adjacenyLists[vertex];
    }
}
