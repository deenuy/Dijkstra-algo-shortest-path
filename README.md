# Dijkstra-algo-shortest-path
Dijkstra's Algorithm for finding shortest path between two cities using PriorityQueues and list nearby cities for given distance k. Assumes all distances are non0negative.

Graph theory is the study of graphs, which are mathematical structures used to model pairwise relations between objects. A graph in this context is made up of vertices (also called nodes or points) which are connected by edges (also called links or lines).

## Implementations of graph algorithms:
- Read CSV and create graph with vertices/ nodes and edges using HashMap<String, List<Edge>>
- Add static method, shortestPath(Graph graph, String from, String to), returning a List of cities visited, from source to the destination, followed by total distance between cities (km)
- Add static method, nearby(Graph graph, String origin, double distance), returning a List of cities with the corresponding distances, for given distance (eg: k=100)

Input file is provided in CSV format, based on Ontario map, listing cities and distances.

### Citations for code adoption:
- Sedgewick, R., Wayne, K. (2011). Algorithms, 4th Edition. Addison-Wesley. ISBN: 978-0-321-57351-3
- Goodrich, M. T., Tamassia, R. (2014). Data Structures and Algorithms in Java, 6th Edition. John Wiley & Sons. ISBN: 0-471-38367-8
