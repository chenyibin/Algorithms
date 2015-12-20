package chenyibin.geeksforgeeks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    AdjListGraph graph;
    boolean[] discovered;
    boolean[] processed;

    List<Integer> sort;
    
    public TopologicalSort(AdjListGraph graph)
    {
        this.graph = graph;
        

    }
    
    List<Integer> getTopologicalSort()
    {
        this.sort = new ArrayList<>();
        // boolean initialized to false by default
        this.discovered = new boolean[graph.getNumVertices()];
        this.processed  = new boolean[graph.getNumVertices()];

        for (int i = 0; i < processed.length; ++i) {
            if (!processed[i]) {
                topologicalSort(i);
            }
        }
        
        Collections.reverse(this.sort);
        return this.sort;
    }

    private void topologicalSort(int i)
    {
        Stack<Integer> dfs = new Stack<>();
        
        dfs.push(i);
        while (!dfs.isEmpty()) {
            int top = dfs.peek();
            if (discovered[top]) {
                dfs.pop();
                if (!processed[top]) {
                    sort.add(top);
                    processed[top] = true;
                }
                continue;
            }
            discovered[top] = true;
            List<Integer> connected = graph.getConnectedTo(top);
            for (int vertex : connected)
            {
                if (!discovered[vertex]) {
                    dfs.push(vertex);
                }
            }
        }
    }
    
}
