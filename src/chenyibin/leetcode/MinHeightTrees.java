package chenyibin.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinHeightTrees {
    
    public List<Integer> findMinHeightTrees(int n, int[][] edges)
    {
        if (n == 1) {
            return Collections.singletonList(0);
        }
        
        Set<Integer>[] adjacencyTable = new HashSet[n];
        for (int i = 0; i < n; ++i) {
            adjacencyTable[i] = new HashSet<>();
        }
        
        // Build the adjacency table for the graph
        for (int[] edge : edges)
        {
            int start = edge[0];
            int end = edge[1];
            adjacencyTable[start].add(end);
            adjacencyTable[end].add(start);
        }
        
        // Initial set of leaves
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; ++i)
        {
            if (adjacencyTable[i].size() == 1) {
                leaves.add(i);
            }
        }
        
        List<Integer> result = leaves;
        List<Integer> newLeaves = new ArrayList<>();
        while (n > 2)
        {
            n -= leaves.size();
            newLeaves.clear();
            for (Integer leaf : leaves) {
                int parent = adjacencyTable[leaf].iterator().next();
                Set<Integer> connected = adjacencyTable[parent];
                connected.remove(leaf);
                if (connected.size() == 1) {
                    newLeaves.add(parent);
                }
            }
            result = newLeaves;
            newLeaves = leaves;
            leaves = result;
        }
        
        return result;
    }
}
