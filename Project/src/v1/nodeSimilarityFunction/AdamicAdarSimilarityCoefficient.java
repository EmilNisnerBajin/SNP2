package v1.nodeSimilarityFunction;

import java.util.Iterator;
import java.util.Set;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class AdamicAdarSimilarityCoefficient<V, E> implements NodeSimilarityFunction<V> {
	
	private UndirectedSparseGraph<V,E> graph;
	
	public AdamicAdarSimilarityCoefficient(UndirectedSparseGraph<V,E> graph) {
		this.graph = graph;
	}

	@Override
	public double computeSimilarity(V first, V second) {
		double similarityScore = 0;
		Set<V> firstNodeNeighbours 	= (Set<V>) graph.getNeighbors(first);
		Set<V> secondNodeNeighbours = (Set<V>) graph.getNeighbors(second);
		
		Iterator<V> nodeIterator = firstNodeNeighbours.iterator();
		V current;
		
		while(nodeIterator.hasNext()) {
			current = nodeIterator.next();
			if(secondNodeNeighbours.contains(current)) {
				similarityScore += 1/Math.log(graph.degree(current));
			}
		}
		return similarityScore;
	}

}
