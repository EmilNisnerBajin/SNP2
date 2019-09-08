package v1.nodeSimilarityFunction;

import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class LengthOfShortestPathSimilarity<V, E> implements NodeSimilarityFunction<V> {

	private UnweightedShortestPath<V,E> shortestPath;
	
	public  LengthOfShortestPathSimilarity(UndirectedSparseGraph<V,E> graph) {
		this.shortestPath = new UnweightedShortestPath<>(graph);
	}
	
	@Override
	public double computeSimilarity(V first, V second) {
		return (double) 1/ (double)shortestPath.getDistance(first, second);
	}

}
