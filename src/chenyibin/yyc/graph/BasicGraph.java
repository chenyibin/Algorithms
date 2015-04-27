package chenyibin.yyc.graph;

import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import chenyibin.yyc.graph.interfaces.IEdge;
import chenyibin.yyc.graph.interfaces.IEdge.EdgeType;
import chenyibin.yyc.graph.interfaces.IGraph;

import com.google.common.collect.Maps;

public class BasicGraph<V, E extends IEdge>
	implements IGraph<V, E>
{
	enum Direction {
		INCOMING,
		OUTGOING,
		INCIDENT
	}
	
	/** 
	 * The graphTable contains the connectivity information between vertices.
	 */
	protected Map<V, EnumMap<Direction, Map<V,E>>> graphTable;

	protected Map<E, Pair<V,V>> directedEdges;
	protected Map<E, Pair<V,V>> undirectedEdges;
	
	public BasicGraph()
	{
		this.graphTable = Maps.newHashMap();
		this.directedEdges = Maps.newHashMap();
		this.undirectedEdges = Maps.newHashMap();
	}
	
	@Override
	public Collection<E> getEdges() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> getVertices() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsVertex(V vertex) {
		return this.graphTable.containsKey(vertex);
	}

	@Override
	public boolean containsEdge(IEdge edge) {
		return this.directedEdges.containsKey(edge)
			|| this.undirectedEdges.containsKey(edge);
	}

	@Override
	public Collection<V> getNeighbors(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<E> getIncidentEdges(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<E> getIncomingEdges(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<E> getOutgoingEdges(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<V, V> getEndPoints(IEdge edge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getSource(IEdge directedEdge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V getDest(IEdge directedEdge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> getPredecessors(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<V> getSuccessors(V vertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addVertex(V vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeVertex(V vertex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEdge(E edge, V v1, V v2) {
		validateAddEdgeArgs(edge, v1, v2);
	
		if (!containsVertex(v1)) addVertex(v1);
		if (!containsVertex(v2)) addVertex(v2);
		
		if (edge.getType() == EdgeType.DIRECTED) {
			addDirectionalEdge(edge, v1, v2, Direction.OUTGOING);
			addDirectionalEdge(edge, v2, v1, Direction.INCOMING);
			this.directedEdges.put(edge, new ImmutablePair<V,V>(v1, v2));
		} else {
			addDirectionalEdge(edge, v1, v2, Direction.INCIDENT);
			addDirectionalEdge(edge, v2, v1, Direction.INCIDENT);
			this.undirectedEdges.put(edge, new ImmutablePair<V,V>(v1,v2));
		}
		return true;
	}
	
	private void validateAddEdgeArgs(E edge, V v1, V v2) {
		if (Objects.isNull(edge))
			throw new NullPointerException("edge may not be null");
		if (Objects.isNull(v1) || Objects.isNull(v2))
			throw new NullPointerException("vertex may not be null");
		if (containsEdge(edge))
			throw new IllegalArgumentException(String.format("edge %s already exists in graph", edge));
	}
	
	private void addDirectionalEdge(E edge, V v1, V v2, Direction direction)
	{
		EnumMap<Direction, Map<V,E>> dirMap = graphTable.get(v1);

		Map<V,E> connection = dirMap.get(direction);
		if (Objects.isNull(connection)) {
			connection = Maps.newHashMap();
			dirMap.put(direction, connection);
		}
		connection.put(v2, edge);
	}
	
	@Override
	public boolean removeEdge(IEdge edge) {
		if (!containsEdge(edge))
			return false; // nothing to remove
			
		Pair<V,V> endpoints = getEndPoints(edge);
		return false;
	}

	@Override
	public E findEdge(V v1, V v2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<E> findEdgeSet(V v1, V v2) {
		// TODO Auto-generated method stub
		return null;
	}

}
