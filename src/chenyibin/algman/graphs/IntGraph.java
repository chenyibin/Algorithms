package chenyibin.algman.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Graph Implementation as described
 * in the Algorithm Design Manual by Skiena.
 * 
 * @author Yibin Chen
 */
public class IntGraph
{
    List<List<EdgeNode>> edgeNodes;
    int numEdges;
    int numNodes;
    int maxDegree;
    boolean isDirected;
    
    static class EdgeNode
    {
        public EdgeNode(int to)
        {
            this.to = to;
            this.weight = 0;
        }
        
        public EdgeNode(int to, int weight)
        {
        	this.to = to;
        	this.weight = weight;
		}
		
		int to;
        int weight;
    }
    
    public IntGraph(int numNodes, boolean directed)
    {
        this.numNodes = numNodes;
        this.edgeNodes = new ArrayList<List<EdgeNode>>(numNodes);
        while (this.edgeNodes.size() < numNodes)
            this.edgeNodes.add(null);
        this.isDirected = directed;
        this.maxDegree = 0;
    }
    
    public void insertEdge(int from, int to) {
    	insertEdge(from, to, 0);
    }
    
    public void insertEdge(int from, int to, int weight)
    {
        if (from >= this.numNodes || from < 0) {
            throw new IndexOutOfBoundsException(
                    "Node " + from + " doesn't exist");
        }
        if (to >= this.numNodes || to < 0) {
            throw new IndexOutOfBoundsException(
                    "Node " + to + " doesn't exist");
        }
        
        insertEdgeInner(from, to, weight);
        if (!this.isDirected) {
            insertEdgeInner(to, from, weight);
        }
        ++numEdges;
    }
    
    private void insertEdgeInner(int from, int to, int weight)
    {
        List<EdgeNode> existingFroms = this.edgeNodes.get(from);
        if (existingFroms == null)
        {
            existingFroms = new LinkedList<EdgeNode>();
            this.edgeNodes.add(from, existingFroms);
        }
        existingFroms.add(new EdgeNode(to, weight));
        
        if (existingFroms.size() > this.maxDegree)
            this.maxDegree = existingFroms.size();
    }
    
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("Number of Nodes: " + this.numNodes + "\n");
        builder.append("Number of Edges: " + this.numEdges + "\n");
        builder.append("Maximum Degree: "  + this.maxDegree + "\n");
        for (int i = 0; i < this.edgeNodes.size(); ++i)
        {
            List<EdgeNode> edges = this.edgeNodes.get(i);
            if (edges == null) continue;
            boolean first = true;
            for (EdgeNode edge : edges)
            {
                if (first) first = false;
                else builder.append(' ');
                    
                builder.append(i + "->" + edge.to);
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
