package chenyibin.leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import chenyibin.leetcode.common.UndirectedGraphNode;

public class CloneGraph
{
    Map<UndirectedGraphNode, UndirectedGraphNode> cloneMap;
    
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node)
    {
        if (node == null) {
            return null;
        }
        this.cloneMap = new HashMap<>();
        
        Set<UndirectedGraphNode> visited = new HashSet<>();
        Stack<UndirectedGraphNode> ts = new Stack<>();
        ts.add(node);
        while (!ts.isEmpty()) {
            UndirectedGraphNode current = ts.pop();
            if (visited.contains(current)) {
                continue;
            } else {
                visited.add(current);
            }
            UndirectedGraphNode clone = getClone(current);
            for (UndirectedGraphNode neighbor : current.neighbors) {
                clone.neighbors.add(getClone(neighbor));
                ts.add(neighbor);
            }
        }
        return getClone(node);
    }
    
    private UndirectedGraphNode getClone(UndirectedGraphNode node)
    {
        UndirectedGraphNode clone = cloneMap.get(node);
        if (clone == null) {
            clone = new UndirectedGraphNode(node.label);
            cloneMap.put(node, clone);
        }
        return clone;
    }
}
