import java.util.List;

/**
 * Stores an undirected graph using an adjacency list representation
 * 
 * @author Catie Baker
 */
public class GraphList<E> extends DiGraphList<E> {

	/**
	 * Creates an undirected graph from the list of edges. Assumes all
	 * vertices have at least one edge
	 * @param edges the edges that are present in the graph
	 */
	public GraphList(List<Pair<E,E>> edges){
		super(edges);
	}
	
	/**
	 * Adds an undirected edge from v1 to v2 to the graph
	 * @param v1 the first vertex of the edges
	 * @param v2 the second vertex of the edge
	 */
	protected void addEdge(E v1, E v2) {
		super.addEdge(v1, v2);
		super.addEdge(v2, v1);
	}
}
