package v1.utils;

import java.util.List;

import edu.uci.ics.jung.graph.util.Pair;
import v1.dataStructure.SignedIntegerEdge;
import v1.interfaces.MyCloneable;
import v1.nodeSimilarityFunction.NodeSimilarityFunction;

public class SimilarityComputer<V extends MyCloneable, E extends SignedIntegerEdge> {

	private NodeSimilarityFunction<V> function;
	
	private List<NodeSetCluster<V>> clusters;
	
	private double valueOfSimilarityBetweenClusters;
	
	private Pair<NodeSetCluster<V>> clusterWithMaxSimilarity;

	
	public SimilarityComputer(NodeSimilarityFunction<V> function ,List<NodeSetCluster<V>> clusters) {
		this.function 									= function;
		this.clusters 									= clusters;
		this.valueOfSimilarityBetweenClusters 			= 0;
	}
	
	
	public void computeSimilarityBetweenClusters() {
		double maxSimilarity = this.valueOfSimilarityBetweenClusters;
		double currentValue  = 0;
		NodeSetCluster<V> first = null;
		NodeSetCluster<V> second = null;
		if(!(clusters.size() <= 1)) {
			for(NodeSetCluster<V> firstCluster : this.clusters) {
				for(NodeSetCluster<V> secondCluster : this.clusters) {
					if(!firstCluster.equals(secondCluster)) {
						currentValue = firstCluster.computeSimilarity(secondCluster, this.function);
						if(currentValue>maxSimilarity) {
							maxSimilarity 	= currentValue;
							first 			= firstCluster;
							second 			= secondCluster;
						}
					}
				}
			}
		}
		this.valueOfSimilarityBetweenClusters 	= maxSimilarity;
		this.clusterWithMaxSimilarity			= new Pair<>(first,second);
	}
	
	public Pair<NodeSetCluster<V>> getPairWithMaxSimilarity(){
		return this.clusterWithMaxSimilarity;
	}
	
	public void setClusters(List<NodeSetCluster<V>> list) {
		this.clusters = list;
	}
}
