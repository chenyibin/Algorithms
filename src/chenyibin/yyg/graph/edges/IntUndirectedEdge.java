package chenyibin.yyg.graph.edges;

public class IntUndirectedEdge extends UndirectedEdge
{
	Integer i;
	
	public static class IntegerUndirectedEdgeFactory
	{
		int counter;
		
		public IntegerUndirectedEdgeFactory(int start) {
			this.counter = start;
		}
		
		public IntUndirectedEdge create() {
			return new IntUndirectedEdge(counter);
		}
	}
	
	public IntUndirectedEdge(int number) {
		i = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i == null) ? 0 : i.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntUndirectedEdge other = (IntUndirectedEdge) obj;
		if (i == null) {
			if (other.i != null)
				return false;
		} else if (!i.equals(other.i))
			return false;
		return true;
	}

	

}
