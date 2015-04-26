package chenyibin.algman.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import chenyibin.algman.graphs.GraphList.EdgeNode;
import chenyibin.algman.graphs.GraphListUtils.VisitState;

public class GraphListBFS
{
    GraphList graph;
    List<VisitState> visitState;
    
    public GraphListBFS(GraphList graph)
    {
        this.graph = graph;
    }
    
    public void resetVisitState()
    {
        this.visitState = new ArrayList<VisitState>(this.graph.numNodes);
        for (int i = 0; i < this.graph.numNodes; ++i)
        {
            this.visitState.add(VisitState.UNDISCOVERED);
        }        
    }
    
    public List<Integer> getTraversal(Integer start)
    {
        resetVisitState();
        List<Integer> traversal = new LinkedList<Integer>();
        
        Deque<Integer> queue = new LinkedList<Integer>();
        queue.offer(start);
        
        while(!queue.isEmpty())
        {
            Integer current = queue.poll();
            // Process current
            traversal.add(current);
            
            List<EdgeNode> edges = this.graph.edgeNodes.get(current);
            if (edges == null)
                edges = Collections.emptyList();
            
            for (EdgeNode edge : edges)
            {
                int toVertex = edge.to;
                if (this.visitState.get(toVertex) == VisitState.UNDISCOVERED)
                {
                    queue.offer(toVertex);
                    this.visitState.set(toVertex, VisitState.DISCOVERED);
                }
            }
            this.visitState.set(current, VisitState.PROCESSED);
        }
        return traversal;
    }

}
