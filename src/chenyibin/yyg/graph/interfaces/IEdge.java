package chenyibin.yyg.graph.interfaces;

public interface IEdge
{
	enum EdgeType {
		DIRECTED,
		UNDIRECTED
	}
	
	public EdgeType getType();
}
