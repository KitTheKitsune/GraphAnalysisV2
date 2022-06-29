import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Driver {
	static String graphType = "";
	static String storeType = "";
	public static <E> void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		//Take in the user info
		System.out.println("What is the graph type?");
		graphType = sc.nextLine();
		System.out.println("How do you want to store the graph?");
		storeType = sc.nextLine();
		System.out.println("What file is the graph stored in?");
		String filename = sc.nextLine();
		
		//Build the Graph
		ArrayList<Pair<E,E>> list = readFile(filename);
		Graph<E> graph = convertToGraph(list);
		
		//Tell if the graph is connected
		GraphAnalysis<E> anal = new GraphAnalysis(graph);
		String result = "";
		if(anal.isConnected()) {
			result = "connected.";
		}else {
			result = "not connected.";
		}
		System.out.println("the graph is " + result);
		
		//Display all the critical vertices
		System.out.println("The following vertices are a vertex cut by themselves:");
		Iterator<E> critical = anal.getCriticalVertices().iterator();
		while(critical.hasNext()) {
			System.out.println(critical.next());
		}
		
		//Check for Vertex cut
		System.out.println("\n \n What is the set you would like to check for a Vertex cut? Please write one vertex per line and END when you are done.");
		Set<E> vCut = new HashSet<>();
		String breaker = "";
		while (!breaker.equals("END")) {
			breaker = sc.nextLine();
			if(breaker.equals("END")) {
				break;
			}
			vCut.add((E)(Integer)Integer.parseInt(breaker));
		}
		if(anal.isVertexCut((Set<E>) vCut)) {
			result = "";
		}else {
			result = "not ";
		}
		System.out.println("This is " + result + "a vertex cut.");
		sc.close();
	}
	
	
	
	/**
	 * 
	 * @param <E>
	 * @param list
	 * @return
	 */
	private static <E> Graph<E> convertToGraph(List<Pair<E,E>> list){
		if (graphType.equals("Undirected") || graphType.equals("undirected")) {
			if (storeType.equals("List") || storeType.equals("list")) {
				//GraphList.java
				return new GraphList(list);	
			}else if(storeType.equals("Matrix") || storeType.equals("matrix")) {
				//GraphMatrix.java
				return new GraphMatrix(list);	
			}else {
				System.out.println("Unknown Storage Method. Try \"Matrix\" or \"List\"");
			}
		}else if(graphType.equals("Directed") || graphType.equals("directed")){
			if (storeType.equals("List") || storeType.equals("list")) {
				//DiGraphList.java
				return new DiGraphList(list);
			}else if(storeType.equals("Matrix") || storeType.equals("matrix")) {
				//DiGraphMatrix.java
				return new DiGraphMatrix(list);	
			}else {
				System.out.println("Unknown Storage Method. Try \"Matrix\" or \"List\"");
			}
		}else {
			System.out.println("Unknown Graph Type. Try \"Undirected\" or \"Directed\"");
		}
		return null;
	}
	
	
	
	
	
	/**
	 * 
	 * @param <E>
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static <E> ArrayList<Pair<E,E>> readFile(String filename) throws IOException {
		String fullFile = fileContentsToString(filename);
		String[] edgeArray = fullFile.split("\n");
		ArrayList<Pair<E,E>> edgeList = new ArrayList<Pair<E,E>>();
		for (int i=0;i < edgeArray.length;i++) {
			String edge = edgeArray[i];
			String[] nil = edge.split(",");
			int x = Integer.parseInt(nil[0]);
			int y = Integer.parseInt(nil[1]);
			edgeList.add(new Pair(x,y));
		}
		return edgeList;
	}
	
	
	
	/**
	 * Converts the contents of a file to a single string
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static String fileContentsToString(String filename) throws IOException {
		return new String(Files.readAllBytes(Paths.get(filename)));
	}

}
