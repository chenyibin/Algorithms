package chenyibin.yyg.graph.edges;

import chenyibin.yyg.graph.interfaces.IEdge;


public abstract class DirectedEdge implements IEdge
{
	public EdgeType getType()
	{
		return EdgeType.DIRECTED;
	}
}
