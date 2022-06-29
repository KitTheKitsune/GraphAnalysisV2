import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Stores a directed graph using a Matrix representation
 * 
 * @author Catie Baker
 */

public class DiGraphMatrix<E> implements Graph<E> {
	private boolean[][] adjMatrix;
	private Map<E,Integer> lookup;
	private E[] vertices;
	
	/**
	 * Creates a directed graph based on the set of edges. Assumes all vertices
	 * have at least 1 edge
	 * @param edges the edges that make up the graph
	 */
	public DiGraphMatrix(List<Pair<E,E>> edges) {
		Set<E> v = new HashSet<E>();
		for(Pair<E,E> edge : edges) {
			v.add(edge.getKey());
			v.add(edge.getValue());
		}
		this.vertices = (E[])(new Object[v.size()]);
		this.lookup = new HashMap<E,Integer>();
		
		int index = 0;
		for(E vertex : v) {
			this.vertices[index] = vertex;
			this.lookup.put(vertex, index);
			index++;
		}
		
		this.adjMatrix = new boolean[index][index];
		
		for(Pair<E,E> edge : edges) {
			this.addEdge(edge.getKey(),edge.getValue());
		}
	}

	/**
	 * Adds an edge from v1 to v2 to the graph
	 * @param v1 the source vertex of the edges
	 * @param v2 the destination vertex of the edge
	 */
	protected void addEdge(E v1, E v2) {
		Integer index1 = this.lookup.get(v1);
		Integer index2 = this.lookup.get(v2);
		if(index1 != null && index2 != null) {
			this.adjMatrix[index1][index2] = true;
		}
	}
	
	/**
	 * Determines if two vertices are adjacent (e.g. there is an
	 * edge between them)
	 * @param v1 the source vertex of the edges
	 * @param v2 the destination vertex of the edge
	 */
	public boolean isAdjacentTo(E v1, E v2) {
		Integer index1 = this.lookup.get(v1);
		Integer index2 = this.lookup.get(v2);
		return index1 != null && index2 != null && this.adjMatrix[index1][index2];
	}
	
	
	/**
	 * Determines all the vertices that are adjacent to v(e.g. there is an
	 * edge between them)
	 * @param v the vertex to find the edges of
	 */
	public Set<E> getAllAdjacent(E v) {
		Set<E> neighbors = new HashSet<E>();
		Integer row = this.lookup.get(v);
		if(row != null) {
			for(int col = 0; col < this.adjMatrix[row].length; col++) {
				if(this.adjMatrix[row][col]) {
					neighbors.add(this.vertices[col]);
				}
			}
		}
		return neighbors;
	}
	
	public Set<E> getVertexes(){
		Set<E> set = new HashSet<E>(Arrays.asList(this.vertices));
		return set;
	}
	
	
	
}
