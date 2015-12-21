package chenyibin.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.primitives.Ints;

/**
 * Given a list of integers get the count of pairs (a, b) such that (a & b == a). 
 * 
 * @author Yibin Chen
 */
public class CountCoveragePairs
{
    private static class CoverageNode
    {
        int value;
        List<CoverageNode> children;
        Set<CoverageNode> descendants;
        
        CoverageNode(int value)
        {
            this.value = value;
            this.children = new ArrayList<>();
        }
        
        CoverageNode insert(int newVal)
        {
            CoverageNode newNode = new CoverageNode(newVal);
            return insert(newNode);
        }
        
        CoverageNode insert(CoverageNode newNode)
        {
            if (find(newNode)) {
                return this;
            }
            if (newNode.covers(this)) {
                newNode.addChild(this);
                return newNode;
            } else if (covers(newNode)) {
                insertInChildren(newNode);
                return this;
            }
            // can't be inserted
            return null;
        }

        private void addChild(CoverageNode child) {
            this.children.add(child);
        }

        private void insertInChildren(CoverageNode newNode) {
            boolean added = false;
            for (int i = 0; i < children.size(); ++i) {
                CoverageNode child = this.children.get(i);
                CoverageNode inserted = child.insert(newNode);
                if (inserted != null) {
                    this.children.set(i, inserted);
                    added = true;
                }
            }
            if (!added) {
                this.children.add(newNode);
            }
        }
 
        private boolean find(CoverageNode newNode) {
            if (newNode.value == this.value) {
                return true;
            }
            
            for (CoverageNode child : children) {
                if (child.find(newNode)) {
                    return true;
                }
            }
            return false;
        }


        boolean covers(CoverageNode other)
        {
            return ((other.value & this.value) == other.value);
        }
        
        public String toString()
        {
            return Integer.toBinaryString(this.value);
        }
        
        Set<CoverageNode> getDescendants()
        {
            if (this.descendants != null) {
                return this.descendants;
            }
            this.descendants = new HashSet<>(children);
            for (CoverageNode child : children) {
                this.descendants.addAll(child.getDescendants());
            }
            return this.descendants;
        }
    }
    
    int countPairs(int[] numbers)
    {
        List<Integer> nums = Ints.asList(numbers);
        Collections.sort(nums, (o1,o2) -> 
            Integer.compare(Integer.bitCount(o2), Integer.bitCount(o1)));
        
        CoverageNode root = new CoverageNode(~0);
        for (int i : numbers) {
            root.insert(i);
        }
        
        Set<CoverageNode> descendants = root.getDescendants();
        int pairCount = 0;
        for (CoverageNode node : descendants) {
            pairCount += node.getDescendants().size();
        }
        return pairCount;
    }
}
