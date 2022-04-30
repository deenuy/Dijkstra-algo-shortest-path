import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graphs {
	/**
	 * Implements a shortest path algorithm based on Dijkstra's
	 * stops as soon as the path to the destination is computed
	 * (does NOT compute the complete shortest path tree)
	 * @param graph input graph
	 * @param from starting vertex
	 * @param to destination
	 * @return List of cities visited, in order, from source to the destination (both are included), 
	 * followed by distance in km 
	 */

	public static List<String> shortestPath(Graph graph, String from, String to) {
		
		List <String> path = new ArrayList<>();
		Map <String, Double> distTo = new HashMap <>();
		Map <String, Edge> edgeTo = new HashMap <>();
		
		// Added for visited nodes		
		HashSet<String> visited = new HashSet<>();

		PriorityQueue <String> verticesPQ = new PriorityQueue<>(new Comparator <String>() {
			@Override
			public int compare(String v1, String v2) {
				return distTo.get(v1)>distTo.get(v2) ? 1 : -1;
			}
		});

		//Set weight of all to infinity except source.
		for (String str: graph.vertices())
			distTo.put(str, Double.POSITIVE_INFINITY);
		
		distTo.put(from, 0.0);
		
    	// return distance as 0, if 'from' equals 'to'
    	if(from.equals(to)) {
    		path.add(from);
    		path.add(to);
    		path.add("" + 0.0);
    		return path;
    	}
    	
    	// Return exception if from or to does not contains in graph
    	if(!distTo.containsKey(from)) 
    		throw new IllegalArgumentException("ERROR: City '" + from + "' does not exists in graph.");
    	
    	// Return exception if from or to does not contains in graph
    	if(!distTo.containsKey(to)) 
    		throw new IllegalArgumentException("ERROR: City '" + to + "' does not exists in graph.");
    	
    	// Shortest Path using Dijkstra's algorithm implementation with Priority queues
		verticesPQ.add(from);
		
		while (!verticesPQ.isEmpty()) {
			
            String v = verticesPQ.poll();
            
            if (!visited.contains(v)) {
            	
            	visited.add(v);

            	// Implementation of relax 
	            for (Edge e : graph.adj.get(v)) {
					Vertex w = (Vertex) e.to();
					double newdist = distTo.get(v).doubleValue() + e.weight();
					
					if (!distTo.containsKey(w.name) || distTo.get(w.name).doubleValue() > newdist) {
						distTo.put(w.name, Double.valueOf(newdist));
						edgeTo.put(w.name, e);
					}
					verticesPQ.add(w.name);
				}
            }
		}
		
		// more cities would be in-between in the proper implementation
		Edge e = edgeTo.get(to);
		path.add(to);
        path.add(e.from().name);
		while ((e = edgeTo.get(e.from().name)) != null) {
            path.add(e.from().name);
        }
		Collections.reverse(path);
		
		// the last item is the total distance, stored as a String 
		path.add("" + distTo.get(to));
		
		return path;
	}

	/**
	 * Utilizes the shortest path algorithm to output a list of cities that are within a specified 
	 * distance from the origin
	 * @param graph input graph
	 * @param origin the origin city
	 * @return List of cities within the specified distance from the origin, ordered from the closest
	 * to the farthest, followed by a distance in km each (quotes just illustrate that those are String objects)
	 * e.g., ["Etobicoke 13", "Mississauga 27.5"]
	 */
	public static List<String> nearby(Graph graph, String origin, double distance) {

		List <String> result = new ArrayList<>();
		List <String> path = new ArrayList<>();
		List <Double> tmp = new ArrayList<>();
		
		Map<Double, String> temp = new HashMap<>();		
		
		for (String dest : graph.vertices()) {
			
			// ignore if dest equals origin
			if (dest.equals(origin)) continue;
			
			// ShortestPath implementation reuse
			path = shortestPath(graph, origin, dest);
			
			double dist =  Double.parseDouble(path.get(path.size() - 1));
			
			if (dist < distance) {
				
				temp.put(dist, dest);
				
				tmp.add(dist);
			}
			Collections.sort(tmp);		
		}
		
		for ( Double d : tmp) {
			if(temp.containsKey(d)){
				// System.out.println(temp.get(d) + " " + d);
				result.add(temp.get(d) + " " + d);
			}
		}

		return result;
	}
	
	//private methods go here
}
