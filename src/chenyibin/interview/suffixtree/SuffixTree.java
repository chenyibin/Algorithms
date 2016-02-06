package chenyibin.interview.suffixtree;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

/**
 * @author Yibin Chen
 */
public class SuffixTree
{
    private static int INFINITY = Integer.MAX_VALUE;
    private SuffixNode root;
    
    // Buffers
    private byte[] buffer;
    private SuffixNode[] leafs;

    private int currentPosition;
    private int bufferSize;
    private int bufferStart;
        
    private int remainingSuffixes;

    // Active Point
    private SuffixNode activeNode;
    private int activeEdgePosition;
    private int activeLength;

    private class SuffixNode
    {
        int startPosition;
        int endPosition;

        int suffixPosition;
        Map<Byte, SuffixNode> next;

        SuffixNode parent;
        SuffixNode suffixLink;

        SuffixNode(int start, int end)
        {
            this.startPosition = start;
            this.suffixPosition = start;
            this.endPosition = end;
            this.next = new HashMap<>();
            this.parent = null;
        }

        int edgeLength() {
            return Math.min(endPosition, SuffixTree.this.currentPosition + 1) - startPosition;
        }

        @Override
        public String toString()
        {
            if (startPosition == -1) {
                return "[root]";
            }
            StringBuilder builder = new StringBuilder();
            builder.append("[s=" + Integer.toString(startPosition));
            if (endPosition == Integer.MAX_VALUE)
            {
                builder.append(":e=oo");
                builder.append(":p=" + Integer.toString(suffixPosition));
            }
            else
            {
                builder.append(":e=" + Integer.toString(endPosition));
            }

            builder.append(":edge=");
            boolean first = true;
            for (int pos = startPosition; pos < endPosition; ++pos)
            {
                if (!isPositionInBuffer(pos)) {
                    break;
                }
                if (first) first = false;
                else builder.append('_');
                builder.append(retrieveFromBuffer(pos));
            }
            builder.append(']');
            return builder.toString();
        }

        /**
         * For Debug
         * @return
         */
        public String detailedString()
        {
            StringBuilder builder = new StringBuilder();
            if (startPosition == -1) {
                builder.append("Node: " + toString() + '\n');
            } else {
                builder.append("Node (len=" + edgeLength() +"): " + toString() + '\n');
            }
            
            boolean first = true;
            for (Entry<Byte, SuffixNode> child : next.entrySet())
            {
                if (first) first = false;
                else builder.append('\n');
                builder.append(" Child: ");
                builder.append(Byte.toString(child.getKey())
                    + "->" + child.getValue().toString());
            }
            if (next.isEmpty()) {
                builder.append(" Children: none");
            }
            builder.append('\n');
            if (suffixLink != null)
            {
                builder.append(" Suffix Link: " + suffixLink.toString());
                builder.append('\n');
            }
            return builder.toString();
        }
    }

    /**
     * @param bufferLength
     */
    public SuffixTree(int bufferLength)
    {
        this.root = new SuffixNode(-1, INFINITY);

        this.buffer = new byte[bufferLength];
        this.leafs = new SuffixNode[bufferLength];

        this.currentPosition = -1;
        this.bufferSize = 0;
        this.bufferStart = 0;

        this.remainingSuffixes = 0;

        this.activeNode = this.root;
        this.activeEdgePosition = 0;
        this.activeLength = 0;
    }

    private byte retrieveFromBuffer(int position)
    {
        return buffer[position];
    }

    private boolean isPositionInBuffer(int position)
    {
        return (position >= 0) && (position < buffer.length);
    }

    /**
     * @param newByte
     */
    public void appendByte(byte newByte)
    {
        if (bufferSize == buffer.length) {
            deletedLongestSuffix();
        } else {
            ++bufferSize;
        }
        currentPosition = (currentPosition + 1) % buffer.length;
        buffer[currentPosition] = newByte;

        updateTree();
    }

    private void updateTree()
    {
        byte currentByte = retrieveFromBuffer(currentPosition);
        ++remainingSuffixes;
        SuffixNode lastSuffixNode = null;
        while (this.remainingSuffixes > 0)
        {
            if (activeLength == 0) {
                activeEdgePosition = currentPosition;
            }
            byte activeEdgeByte = retrieveFromBuffer(activeEdgePosition);
            SuffixNode activeNext = activeNode.next.get(activeEdgeByte);
            if (activeNext == null)
            {
                SuffixNode leafNode = createLeaf(currentPosition);
                putChild(activeNode, activeEdgeByte, leafNode);
                lastSuffixNode = updateLastSuffixLink(lastSuffixNode, activeNode);
            }
            else
            {
                if (walkDown(activeNext)) {
                    continue;
                }
                int activePosition = activeNext.startPosition + activeLength;
                byte activePositionByte = retrieveFromBuffer(activePosition);
                if (activePositionByte == currentByte)
                {
                    ++activeLength;
                    lastSuffixNode = updateLastSuffixLink(lastSuffixNode, activeNode);
                    break;
                }

                // Create a split node with one branch to the activeNext node
                // and another branch to the newLeaf node
                SuffixNode splitNode = new SuffixNode(activeNext.startPosition, activePosition);
                putChild(activeNode, activeEdgeByte, splitNode);

                SuffixNode newLeaf = createLeaf(currentPosition);
                putChild(splitNode, currentByte, newLeaf);

                activeNext.startPosition += activeLength;
                byte splitToActiveNext = retrieveFromBuffer(activeNext.startPosition);

                putChild(splitNode, splitToActiveNext, activeNext);

                lastSuffixNode = updateLastSuffixLink(lastSuffixNode, splitNode);
            }

            --remainingSuffixes;

            if (activeNode == root && activeLength > 0)
            {
                // active edge must be first character of next suffix to be inserted
                activeEdgePosition = currentPosition - remainingSuffixes + 1;
                // next suffix is current suffix without first character
                // so decrement active length by 1
                --activeLength;
            }
            else if (activeNode.suffixLink == null)
            {
                activeNode = root;
            }
            else
            {
                // suffixNode pointed to by suffixLink represents the same suffix
                // as the current node minus the first character
                activeNode = activeNode.suffixLink;
            }
        }
    }
    
    private SuffixNode createLeaf(int startPosition)
    {
        SuffixNode leaf = new SuffixNode(startPosition, INFINITY);
        int leafIndex = startPosition % this.leafs.length;
        this.leafs[leafIndex] = leaf;
        return leaf;
    }

    private SuffixNode updateLastSuffixLink(SuffixNode lastSuffixNode,
        SuffixNode nextSuffixNode)
    {
        if (lastSuffixNode != null)
        {
            lastSuffixNode.suffixLink = nextSuffixNode;
            if (nextSuffixNode == root) {
                return null;
            }
        }

        return nextSuffixNode;
    }

    public void deletedLongestSuffix()
    {
        canonize();
        SuffixNode leaf = this.leafs[bufferStart];
        this.leafs[bufferStart] = null;
        
        SuffixNode parent = leaf.parent;
        if (parent == activeNode)
        {
        }
        else
        {
            byte leafEdge = retrieveFromBuffer(leaf.startPosition);
            parent.next.remove(leafEdge);
            if (parent != root && parent.next.size() == 1)
            {
                // If only one child remains in parent and it's not the root
                // we need to remove the parent to maintain the path compression property
                SuffixNode ancestor = parent.parent;
                SuffixNode otherNode = parent.next.entrySet().iterator().next().getValue();
                byte parentEdge = retrieveFromBuffer(parent.startPosition);
                otherNode.startPosition = otherNode.startPosition - parent.edgeLength();
                ancestor.next.put(parentEdge, otherNode);

                assert retrieveFromBuffer(otherNode.startPosition) == parentEdge;
            }
        }

        bufferStart = (bufferStart + 1) % buffer.length;
    }

    /**
     * @param search
     * @return
     * <li>Return pair of match position and match length given search array.</li>
     * <li>Return null if no match was found</li>
     */
    public int[] findLongestMatch(byte[] search)
    {
        SuffixNode current = root;

        int matchPosition = -1;
        int lastEdgePosition = -1;

        int matched = 0;
        for (;matched < search.length; ++matched)
        {
            byte searchByte = search[matched];

            if (matchPosition >= lastEdgePosition)
            {
                SuffixNode node = current.next.get(searchByte);
                if (node == null) {
                    break;
                }
                matchPosition = node.startPosition;
                lastEdgePosition = matchPosition + node.edgeLength() - 1;
                current = node;
            }
            else
            {
                int checkPosition = matchPosition + 1;
                if (!isPositionInBuffer(checkPosition)) {
                    break;
                }
                byte checkByte = retrieveFromBuffer(checkPosition);
                if (checkByte != searchByte) {
                    break;
                }
                matchPosition = checkPosition;
            }
        }

        if (matchPosition < 0) {
            return null;
        }
        return new int[] {matchPosition - matched + 1, matched};
    }

    private boolean walkDown(SuffixNode activeNext)
    {
        if (activeNext.endPosition == INFINITY) {
            return false;
        }

        int nextEdgeLength = activeNext.edgeLength();
        if (activeLength >= nextEdgeLength)
        {
            activeEdgePosition += nextEdgeLength;
            activeLength -= nextEdgeLength;
            activeNode = activeNext;
            return true;
        }
        return false;
    }

    private void canonize()
    {
        SuffixNode activeNext = null;
        do {
            byte activeEdgeByte = retrieveFromBuffer(activeEdgePosition);
            activeNext = activeNode.next.get(activeEdgeByte);
        } while (walkDown(activeNext));
    }


    private static void putChild(SuffixNode parent, byte key, SuffixNode child)
    {
        parent.next.put(key, child);
        child.parent = parent;
    }

    /**
     * For Debug
     * @param out
     */
    public void print(PrintStream out)
    {
        out.println(bufferToNumericString());
        Stack<SuffixNode> traversalStack = new Stack<>();
        traversalStack.push(root);
        while (!traversalStack.isEmpty())
        {
            SuffixNode current = traversalStack.pop();
            out.print(current.detailedString());
            traversalStack.addAll(current.next.values());
        }
    }

    /**
     * For Debug
     * @return
     */
    public String bufferToNumericString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("BUFFER: ");
        boolean first = true;
        for (byte b : buffer)
        {
            if (first) first = false;
            else builder.append(' ');
            builder.append(Byte.toString(b));
        }
        return builder.toString();
    }
}
