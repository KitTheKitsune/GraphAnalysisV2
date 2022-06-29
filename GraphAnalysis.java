import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * A Program to determine if a given graph is strongly connected
 * @author Kendrick Smith
 * @param <E>
 *
 */

public class GraphAnalysis<E> {
	Graph<E> graph;
	TreeMap<E,Boolean> activeV;

	/**
	 * Take a graph object and store it to run analysis
	 * @param g
	 */
	public GraphAnalysis(Graph<E> g) {
		this.graph = g;
		this.activeV = new TreeMap<E,Boolean>();
		
		Iterator<E> it = this.graph.getVertexes().iterator();
		while(it.hasNext()) {
			this.activeV.put(it.next(), true);
		}
	}
	
	/**
	 * This method will determine if the graph is connected.
	 * That is, there is a path between all pairs of vertices.
	 * @return
	 */
	public boolean isConnected() {
		Set<E> v = this.graph.getVertexes();
		E x;
		Set<E> set = new HashSet<E>();
		Iterator<E> it = v.iterator();
		while(it.hasNext()) {
			x = it.next();
			if(this.activeV.get(x)) {
				set.addAll(this.graph.getAllAdjacent(x));
			}
		}
		
		return set.equals(v);
	}
	
	/**
	 * Determines if a set of vertices is a vertex cut
	 * @param vertices
	 * @return
	 */
	public boolean isVertexCut(Set<E> vertices) {
		Iterator<E> it = vertices.iterator();
		Iterator<E> mit = this.graph.getVertexes().iterator();
		while(it.hasNext()) {
			var x = it.next();
			if( mit.next().getClass() == x.getClass()) {
				this.activeV.replace(x, false);
			}else {
				this.activeV.put(x, false);
			}
		}
		return isConnected();
	}
	
	/**
	 * 
	 * @return
	 */
	public Set<E> getCriticalVertices(){
		HashSet<E> output = new HashSet<E>();
		E x;
		Iterator<E> it = this.graph.getVertexes().iterator();
		while(it.hasNext()) {
			HashSet<E> set = new HashSet<E>();
			set.add(x = it.next());
			if(isVertexCut(set)) {
				output.add(x);
			}
		}
		
		return output;
	}
	
	

}
