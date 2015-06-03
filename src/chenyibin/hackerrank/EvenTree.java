package chenyibin.hackerrank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * <strong>Problem:</strong></br>
 * You are given a tree (a simple connected graph with no cycles).</br>
 * Remove as many edges from the tree as possible to obtain a forest where</br>
 * each connected component of the forest contains an even number of vertices.</br>
 * </br>
 * <strong>Solution:</strong></br>
 * Think of each edge in the tree as connecting two smaller trees.</br>
 * Removing one edge will always result in two smaller trees.</br>
 * If after an edge is removed, the two smaller trees have an even number</br>
 * of nodes, then the condition to our problem is satisfied.</br>
 * </br>
 * Starting at an arbitrary root node (it doesn't matter which node you pick),</br>
 * we can perform a depth first traversal determine the number of nodes for</br>
 * each sub-tree. If the number of nodes in the sub-tree is even, then it can</br>
 * be cut off from the main tree iff the number of nodes remaining in the main</br>
 * tree is also even. Note that we don't actually have to modify the original</br>
 * tree to determine this property due to even-ness. So building an array</br>
 * containing the number of nodes in each sub-tree is sufficient for figuring</br>
 * out the maximum number of cuts possible.</br>
 * </br>
 * <strong>Note:</strong> The problem of determining sub-tree size is trivially</br>
 * solved using recursion but we're going to do this thing iteratively as an</br>
 * exercise and because it's slightly more efficient to maintain your own stack.</br>
 * 
 * @author Yibin Chen
 */
public class EvenTree {
	
	int numVertices;
	int numEdges;
	
	/* Edges are represented as a map from one node to a set of other nodes.
	 * A tree can be thought of as an undirected graph with no cycles. */
	Map<Integer, Set<Integer>> edges;

	int[] subTreeSizes;
	Integer treeRoot;
	
	File testFile;

	public EvenTree() {
		this.numVertices = 0;
		this.numEdges = 0;
		this.edges = null;

		this.subTreeSizes = null;
		this.treeRoot = null;
		this.testFile = null;
	}
	
	public void setTestFile(File testFile) {
		this.testFile = testFile;
	}
	
	public boolean readInput() {
		Scanner scn = null;
		if (this.testFile != null) {
			try {
				scn = new Scanner(this.testFile);
			} catch (FileNotFoundException e) {
				System.err.println("Unable to read file");
				System.exit(1);
			}
		} else {
			scn = new Scanner(System.in);
		}
		this.numVertices = scn.nextInt();
		this.numEdges = scn.nextInt();
		
		if (numVertices % 2 != 0) {
			scn.close();
			return false;
		}
		
		this.edges = new HashMap<Integer,Set<Integer>>(this.numVertices);
		
		for (int i = 0; i < this.numEdges; ++i) {
			int v1 = scn.nextInt() - 1;
			int v2 = scn.nextInt() - 1;
			addEdge(v1, v2);
		}
		scn.close();
			
		return true;
	}
	
	private void addEdge(int v1, int v2)
	{
		/* The problem would be simpler if we used a directional graph.
		 * For the input from hackerrank this problem is solveable with
		 * a directional graph since each edge v1 -> v2 satisfies v1 < v2.
		 * But, we're going to solve it for the general case anyways. */
		addEdgeDirectional(v1, v2);
		addEdgeDirectional(v2, v1);
	}
	
	private void addEdgeDirectional(int from, int to)
	{
		Set<Integer> tos = this.edges.get(from);
		if (tos == null)
		{
			tos = new HashSet<Integer>();
			this.edges.put(from, tos);
		}
		tos.add(to);
	}
	
	public void solve()
	{
		// Pick a root
		this.treeRoot     = this.edges.keySet().iterator().next();
		this.subTreeSizes = new int[this.numVertices];

		Deque<Integer> traversal = new LinkedList<Integer>();
		// The post stack acts both as a way to figure out
		// the parent for our current node as well as a marker for
		// nodes that we've discovered but haven't processed yet
		Deque<Integer> post      = new LinkedList<Integer>();

		// Do a depth first traversal of the tree starting at treeRoot
		traversal.push(this.treeRoot);
		while (!traversal.isEmpty()) {
			Integer current = traversal.peek();
			if (post.peek() == current) {
				post.pop();
				traversal.pop();
				Integer parent = post.peek();
				if (parent == null) {
					continue;
				}
				int subTreeSize = 1 + sumChildNodes(parent, current);
				this.subTreeSizes[current] = subTreeSize;
			} else {
				Integer parent = post.peek();
				post.push(current); // mark node as discovered
				for (Integer child : this.edges.get(current)) {
					if (!child.equals(parent)) {
						traversal.push(child);
					}
				}
			}
		}
		
		// Calculate the number of possible cuts
		int numSubTrees = 0;
		for (int subTreeSize : this.subTreeSizes) {
			if (subTreeSize % 2 == 0 && (this.numVertices - subTreeSize) % 2 == 0) {
				++numSubTrees;
			}
		}
		System.out.println(numSubTrees - 1);
	}
	
	private int sumChildNodes(int parent, Integer current)
	{
		int numNodes = 0;
		for (int child : this.edges.get(current)) {
			// Note that since we treat the graph as undirectional
			// we need to ensure that the parent isn't counted
			if (child != parent)
				numNodes += this.subTreeSizes[child];
		}
		return numNodes;
	}
	
	public static void main(String args[])
	{
		EvenTree solver = new EvenTree();
		if (args.length > 0) {
			solver.setTestFile(new File(args[0]));
		}
		
		boolean solveable = solver.readInput();
		
		// Unnecessary by programming description but we'll check anyways
		if (!solveable) {
			System.out.println(0);
			return;
		}
		solver.solve();
	}

}
