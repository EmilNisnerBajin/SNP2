package v1.utils;

import java.util.List;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import v1.dataStructure.SignedIntegerEdge;
import v1.interfaces.MyCloneable;
import v1.nodeSimilarityFunction.NodeSimilarityFunction;

public class ClusterAnalyser<V extends MyCloneable, E extends SignedIntegerEdge> {

	private Dendrogram<V,E> dendrogram;
	
	public ClusterAnalyser(UndirectedSparseGraph<V,E> graph, NodeSimilarityFunction<V> function) {
		this.dendrogram = new Dendrogram<>(graph, function);
	}
	
	public ClusterAnalyser(UndirectedSparseGraph<V,E> graph) {
		this.dendrogram = new Dendrogram<>(graph);
	}
	
	public List<NodeSetCluster<V>> bestPartition(){
		return dendrogram.getBestPartition().getClusters();
	}

}
