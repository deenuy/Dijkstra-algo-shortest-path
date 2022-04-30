import java.util.ArrayList;
import java.util.List;

public class A4demo {

	public static void main(String[] args) {
		Graph g = new DistanceGraph("Edges.csv");
//		System.out.println(g);

		String from = "Toronto";
		String to = "Burlington";
		System.out.println(Graphs.shortestPath(g, from, to));

		List<String> cities = new ArrayList<>();
		cities.add("Ajax"); cities.add("Oshawa"); cities.add("Markham"); cities.add("Toronto");
		//start in Ajax, end in Toronto; 
		//visit Oshawa and Markham along the way, in the order so that the total path is the shortest
		System.out.println(Graphs.nearby(g, "Toronto", 30));
	}
}