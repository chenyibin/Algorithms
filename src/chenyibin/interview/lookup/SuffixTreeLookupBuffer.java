package chenyibin.interview.lookup;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

/**
 * Suffix Tree Sliding Window implementation based on
 * Ukkonen's algorithm description found on
 * <a href="http://stackoverflow.com/questions/9452701/ukkonens-suffix-tree-algorithm-in-plain-english">
 * stackoverflow</a> and publications by <a href="http://www.itu.dk/people/jesl/">N. Jesper Larsson</a>.
 * Most of the naming conventions such as active point, active node,
 * active edge, active length, canonize, etc. originate from those sources.
 *
 * @author Yibin Chen
 */
public class SuffixTreeLookupBuffer implements LookupBuffer
{
    private static int INFINITY = Integer.MAX_VALUE;

    private SuffixNode root;

    private byte[] buffer;

    // Required for O(1) access to leaf nodes during longest suffix deletion
    private SuffixNode[] leafs;
    // How many leaf nodes we've created so far
    private int leafCounter;

    private int currentPosition;
    private int bufferSize;
    private int bufferStart;

    private int remainingSuffixes;

    // Active Point
    private SuffixNode activeNode;
    private int activeEdgePosition;
    private int activeLength;

    // Suffix Link Memory
    private SuffixNode lastSuffixNode;

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
            this.endPosition = end;
            this.next = new HashMap<>();
            this.parent = null;
        }

        int edgeLength() {
            return Math.min(endPosition, SuffixTreeLookupBuffer.this.currentPosition + 1) - startPosition;
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
            for (int pos = startPosition; pos < endPosition && pos <= currentPosition; ++pos)
            {
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
                builder.append(" Leaf");
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
    public SuffixTreeLookupBuffer(int bufferLength)
    {
        this.root = new SuffixNode(-1, INFINITY);

        this.buffer = new byte[bufferLength];

        this.leafs = new SuffixNode[bufferLength];
        this.leafCounter = 0;

        this.currentPosition = -1;
        this.bufferSize = 0;
        this.bufferStart = 0;

        this.remainingSuffixes = 0;

        this.activeNode = this.root;
        this.activeEdgePosition = 0;
        this.activeLength = 0;

        this.lastSuffixNode = null;
    }

    private byte retrieveFromBuffer(int position)
    {
        return buffer[position % buffer.length];
    }

    private boolean isPositionInBuffer(int position)
    {
        return (position >= bufferStart) && (position <= currentPosition);
    }

    /**
     * @param newByte
     */
    public void appendByte(byte newByte)
    {
        if (bufferSize == buffer.length) {
            deletedLongestSuffix();
            bufferStart = (bufferStart + 1) % buffer.length;
        } else {
            ++bufferSize;
        }
        ++currentPosition;
        buffer[currentPosition % buffer.length] = newByte;

        ++remainingSuffixes;
        updateTree();
    }

    private void updateTree()
    {
        lastSuffixNode = null;
        byte currentByte = retrieveFromBuffer(currentPosition);
        while (this.remainingSuffixes > 0)
        {
            if (activeLength == 0) {
                activeEdgePosition = currentPosition;
            }
            byte activeEdgeByte = retrieveFromBuffer(activeEdgePosition);
            SuffixNode activeNext = activeNode.next.get(activeEdgeByte);
            if (activeNext == null)
            {
                --remainingSuffixes;
                SuffixNode leafNode = createLeaf(currentPosition, currentPosition - remainingSuffixes);
                putChild(activeNode, activeEdgeByte, leafNode);
                updateLastSuffixLink(activeNode);
            }
            else
            {
                if (activeWalkDown(activeNext)) {
                    continue;
                }
                int activePosition = activeNext.startPosition + activeLength;
                byte activePositionByte = retrieveFromBuffer(activePosition);
                if (activePositionByte == currentByte)
                {
                    ++activeLength;
                    updateLastSuffixLink(activeNode);
                    break;
                }

                // Create a split node with one branch to the activeNext node
                // and another branch to the newLeaf node
                SuffixNode splitNode = new SuffixNode(activeNext.startPosition, activePosition);
                putChild(activeNode, activeEdgeByte, splitNode);

                --remainingSuffixes;
                SuffixNode newLeaf = createLeaf(currentPosition, currentPosition - remainingSuffixes);
                putChild(splitNode, currentByte, newLeaf);

                activeNext.startPosition += activeLength;
                byte splitToActiveNext = retrieveFromBuffer(activeNext.startPosition);

                putChild(splitNode, splitToActiveNext, activeNext);

                updateLastSuffixLink(splitNode);
            }

            followSuffixLink();
        }
    }

    private void followSuffixLink()
    {
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

    private SuffixNode createLeaf(int startPosition, int suffixPosition)
    {
        SuffixNode leaf = new SuffixNode(startPosition, INFINITY);
        leaf.suffixPosition = suffixPosition;
        assert leaf.suffixPosition == leafCounter;
        ++leafCounter;

        int leafIndex = leaf.suffixPosition % this.leafs.length;
        this.leafs[leafIndex] = leaf;
        return leaf;
    }

    private void updateLastSuffixLink(SuffixNode nextSuffixNode)
    {
        if (lastSuffixNode != null)
        {
            lastSuffixNode.suffixLink = nextSuffixNode;
        }

        lastSuffixNode = nextSuffixNode;
    }

    private void deletedLongestSuffix()
    {
        canonizeActivePoint();
        SuffixNode leaf = this.leafs[bufferStart];
        this.leafs[bufferStart] = null;
        byte leafEdgeByte = retrieveFromBuffer(leaf.startPosition);

        SuffixNode parent = leaf.parent;
        parent.next.remove(leafEdgeByte);

        if (parent == activeNode && activeLength > 0)
        {
            byte activeEdgeByte = retrieveFromBuffer(activeEdgePosition);
            if (leafEdgeByte == activeEdgeByte)
            {
                // If the active point is on the leaf edge we are removing
                // create a 'trimmed' leaf in its place with the correct starting position.
                --remainingSuffixes;
                SuffixNode newLeaf = createLeaf(activeEdgePosition, currentPosition - remainingSuffixes);
                putChild(parent, leafEdgeByte, newLeaf);
                updateAncestralPositioning(newLeaf);
                followSuffixLink();
                return;
            }
        }

        if (parent != root && parent.next.size() == 1)
        {
            // If only one child remains in parent and it's not the root
            // we need to remove the parent to maintain the path compression property
            SuffixNode ancestor = parent.parent;

            // Last node standing needs to be attached to ancestor
            SuffixNode otherNode = parent.next.values().iterator().next();
            int parentEdgeLength = parent.edgeLength();
            byte parentEdgeByte = retrieveFromBuffer(parent.startPosition);

            // Since startPosition is the position of the incoming edge
            // we need to update startPosition to be outgoing from the ancestor
            otherNode.startPosition = otherNode.startPosition - parentEdgeLength;
            putChild(ancestor, parentEdgeByte, otherNode);
            assert retrieveFromBuffer(otherNode.startPosition) == parentEdgeByte;

            updateAncestralPositioning(otherNode);

            if (parent == activeNode) {
                activeNode = ancestor;
                activeEdgePosition = otherNode.startPosition;
                activeLength += parentEdgeLength;
            }
        }
    }

    /**
     * @param node
     */
    private void updateAncestralPositioning(SuffixNode node)
    {
        SuffixNode parent = node.parent;
        while (parent != root)
        {
            int parentLength = parent.edgeLength();
            parent.startPosition = node.startPosition - parentLength;
            parent.endPosition = parent.startPosition + parentLength;
            assert parent.startPosition > 0;
            node = parent;
            parent = parent.parent;
        }
    }

    @Override
    public int[] findLongestMatch(byte[] search)
    {
        return findLongestMatch(search, 0, search.length);
    }

    @Override
    public int[] findLongestMatch(byte[] lookup, int start, int length)
    {
        SuffixNode currentNode = root;

        int matchPosition = -1;
        int lastEdgePosition = -1;

        int matched = 0;
        int pos = start;
        for (int i = 0; i < length; ++i)
        {
            byte searchByte = lookup[pos];

            if (matchPosition >= lastEdgePosition)
            {
                // we reached the end of our current node
                // so we need to get the next one
                SuffixNode node = currentNode.next.get(searchByte);
                if (node == null) {
                    break;
                }
                matchPosition = node.startPosition;
                lastEdgePosition = matchPosition + node.edgeLength() - 1;
                currentNode = node;
            }
            else
            {
                // advance from last matched position
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
            pos = (pos + 1) % lookup.length;
            ++matched;
        }

        if (matchPosition < 0) {
            return null;
        }
        int offset = currentPosition - matchPosition + matched;
        return new int[] {offset, matched};
    }

    private boolean activeWalkDown(SuffixNode activeNext)
    {
        if (activeNext == null) {
            return false;
        }
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

    private void canonizeActivePoint()
    {
        SuffixNode activeNext = null;
        do {
            if (activeLength == 0) {
                return;
            }
            byte activeEdgeByte = retrieveFromBuffer(activeEdgePosition);
            activeNext = activeNode.next.get(activeEdgeByte);
        } while (activeWalkDown(activeNext));
    }


    private static void putChild(SuffixNode parent, byte key, SuffixNode child)
    {
        parent.next.put(key, child);
        child.parent = parent;
    }

    /**
     * For Test and Debug
     * @param out
     */
    void print(PrintStream out)
    {
        out.println(bufferToNumericString());
        out.println(" Active Point: " + activeNode.toString());
        out.println(" Active Edge: " + activeEdgePosition);
        out.println(" Active Length: " + activeLength);
        out.println(" Remaining: " + remainingSuffixes);
        Stack<SuffixNode> traversalStack = new Stack<>();
        traversalStack.push(root);
        while (!traversalStack.isEmpty())
        {
            SuffixNode current = traversalStack.pop();
            out.print(current.detailedString());
            traversalStack.addAll(current.next.values());
        }
        System.out.println();
    }

    /**
     * For Test and Debug
     * @return
     */
    String bufferToNumericString()
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

    /**
     * For Test and Debug
     * @return
     */
    int getLeafCounter() {
        return leafCounter;
    }
}
