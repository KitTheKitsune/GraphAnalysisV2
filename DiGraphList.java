import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Stores a directed graph using an adjacency list representation
 * 
 * @author Catie Baker
 */
public class DiGraphList<E> implements Graph<E> {
	private Map<E, Set<E>> adjList;
	private Set<E> vertices;
	
	/**
	 * Creates a directed graph based on the set of edges. Assumes all vertices
	 * have at least 1 edge
	 * @param edges the edges that make up the graph
	 */
	public DiGraphList(List<Pair<E,E>> edges ) {
		this.adjList = new HashMap<E, Set<E>>();
		this.vertices = new HashSet<E>();
		for(Pair<E,E> edge : edges) {
			addEdge(edge.getKey(), edge.getValue());
			vertices.add(edge.getKey());
			vertices.add(edge.getValue());
		}
	}
	
	/**
	 * Adds an edge from v1 to v2 to the graph
	 * @param v1 the source vertex of the edges
	 * @param v2 the destination vertex of the edge
	 */
	protected void addEdge(E v1, E v2) {
		if(!this.adjList.containsKey(v1)) {
			this.adjList.put(v1, new HashSet<E>());
		}
		this.adjList.get(v1).add(v2);
	}

	/**
	 * Determines if two vertices are adjacent (e.g. there is an
	 * edge between them)
	 * @param v1 the source vertex of the edges
	 * @param v2 the destination vertex of the edge
	 */
	public boolean isAdjacentTo(E v1, E v2) {
		return this.adjList.containsKey(v1) && this.getAllAdjacent(v1).contains(v2);
	}

	/**
	 * Determines all the vertices that are adjacent to v(e.g. there is an
	 * edge between them)
	 * @param v the vertex to find the edges of
	 */
	public Set<E> getAllAdjacent(E v) {
		if(this.adjList.containsKey(v)) {
			return this.adjList.get(v);
		}
		else {
			return new HashSet<E>();
		}
	}
	
	public Set<E> getVertexes(){
		return this.vertices;
	}
	
	

}
