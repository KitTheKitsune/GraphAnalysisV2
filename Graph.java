import java.util.Set;
/** 
 * An interface that defines a generic graph.  
 * @author Catie Baker 
 */

public interface Graph<E> {
	/** 
	 * Determines if two vertices are adjacent (e.g. there is an edge between them) 
	 * @param v1 the source vertex of the edges 
	 * @param v2 the destination vertex of the edge 
	 */
	boolean isAdjacentTo(E v1, E v2);
	/** 
	 * Determines all the vertices that are adjacent to v(e.g. there is an edge between them) 
	 * @param v the vertex to find the edges of 
	 * @return the set of vertices that are adjacent to v 
	 */
	Set<E> getAllAdjacent(E v);
	
	Set<E> getVertexes();
}
 