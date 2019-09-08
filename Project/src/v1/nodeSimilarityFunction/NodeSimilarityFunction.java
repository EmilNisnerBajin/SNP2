package v1.nodeSimilarityFunction;

public interface NodeSimilarityFunction<V> {

	public double computeSimilarity(V first, V second);
}
