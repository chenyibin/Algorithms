package chenyibin.yyc.graph.edges;

import chenyibin.yyc.graph.interfaces.IEdge;


public abstract class DirectedEdge implements IEdge
{
	public EdgeType getType()
	{
		return EdgeType.DIRECTED;
	}
}
