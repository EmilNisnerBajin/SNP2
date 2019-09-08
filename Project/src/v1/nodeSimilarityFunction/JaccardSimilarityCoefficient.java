package v1.nodeSimilarityFunction;

import java.util.Iterator;
import java.util.Set;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

public class JaccardSimilarityCoefficient<V, E> implements NodeSimilarityFunction<V> {

	private UndirectedSparseGraph<V,E> graph;
	
	public JaccardSimilarityCoefficient(UndirectedSparseGraph<V,E> graph) {
		this.graph = graph;
	}
	
	@Override
	public double computeSimilarity(V first, V second) {
		Set<V> firstNodeNeighbours 	= (Set<V>) graph.getNeighbors(first);
		Set<V> secondNodeNeighbours = (Set<V>) graph.getNeighbors(second);
		double result;
		int mutualNeighbours 	= 0;
		int distinctNeighbours	= 0;
			
		V current;
		Iterator<V> nodeIterator = firstNodeNeighbours.iterator();
		
		while(nodeIterator.hasNext()) {
			current = nodeIterator.next();
			if(secondNodeNeighbours.contains(current)) {
				mutualNeighbours ++;
			}else {
				distinctNeighbours ++;
			}
		}
		
		result = (double) mutualNeighbours / distinctNeighbours;
		
		return result;
	}

}
