package chenyibin.yyc.graph.interfaces;

public interface IEdge
{
	enum EdgeType {
		DIRECTED,
		UNDIRECTED
	}
	
	public EdgeType getType();
}
