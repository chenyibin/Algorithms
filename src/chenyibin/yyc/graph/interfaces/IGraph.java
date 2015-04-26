package chenyibin.yyc.graph.interfaces;

import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

public interface IGraph<V,E extends IEdge>
{
	/**
	 * @return
	 */
    Collection<E> getEdges();
    
    /**
     * @return
     */
    Collection<V> getVertices();
    
    /**
     * @param vertex
     * @return
     */
    boolean containsVertex(V vertex);
    /**
     * @param edge
     * @return
     */
    boolean containsEdge(E edge);
    
    /**
     * May include <code>vertex</vertex> due to self loop
     * @param vertex
     * @return
     */
    Collection<V> getNeighbors(V vertex); 
    
    /**
     * @param vertex
     * @return
     */
    Collection<E> getIncidentEdges(V vertex);
    
    /**
     * @param vertex
     * @return
     */
    Collection<E> getIncomingEdges(V vertex); 
    
    /**
     * @param vertex
     * @return
     */
    Collection<E> getOutgoingEdges(V vertex);

    /**
     * @param edge
     * @return
     */
    Pair<V,V> getEndPoints(E edge);
    
    /**
     * @param directedEdge
     * @return
     */
    V getSource(E directedEdge);
    /**
     * @param directedEdge
     * @return
     */
    V getDest(E directedEdge);
    
    /**
     * A predecessor of <code>vertex</code> is defined as a vertex <code>v</code> 
     * which is connected to 
     * <code>vertex</code> by an edge <code>e</code>, where <code>e</code> is an outgoing edge of 
     * <code>v</code> and an incoming edge of <code>vertex</code>.
     * @param vertex
     * @return predecessors
     */
    Collection<V> getPredecessors(V vertex);
    
    /**
     * A successor of <code>vertex</code> is defined as a vertex <code>v</code> 
     * which is connected to <code>vertex</code> by an edge <code>e</code>,
     * where <code>e</code> is an incoming edge of <code>v</code> and
     * an outgoing edge of <code>vertex</code>.
     * @param vertex
     * @return successors
     */
    Collection<V> getSuccessors(V vertex);

    /**
     * @param vertex
     * @return
     */
    boolean addVertex(V vertex);
    /**
     * @param vertex
     * @return
     */
    boolean removeVertex(V vertex);
    
    /**
     * @param edge
     * @param v1
     * @param v2
     * @return
     */
    boolean addEdge(E edge, V v1, V v2);
    /**
     * @param edge
     * @return
     */
    boolean removeEdge(E edge);
    
    /**
     * @param v1
     * @param v2
     * @return
     */
    E findEdge(V v1, V v2);
    /**
     * @param v1
     * @param v2
     * @return
     */
    Collection<E> findEdgeSet(V v1, V v2);
}