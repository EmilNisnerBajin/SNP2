package v1.dataStructure;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import v1.nodeSimilarityFunction.NodeSimilarityFunction;

public class SignedIntegerCluster<V , E extends SignedIntegerEdge>{

	private UndirectedSparseGraph<V,E> cluster;
	private boolean coalition;
	private List<SignedIntegerEdge> opposingCoalitionEdges;
	 
	public SignedIntegerCluster(UndirectedSparseGraph<V,E> graph) {
		this.cluster 					= graph;
		this.coalition					= true;
		this.opposingCoalitionEdges		= new LinkedList<>();
	}
	
	public UndirectedSparseGraph<V,E> getCluster(){
		return cluster;
	}
	
	public boolean isCoalition() {
		return coalition;
	}

	public void setCoaliton(boolean isCoaliton) {
		this.coalition = isCoaliton;
	}
	
	public void setOpposignEdges(List<SignedIntegerEdge> list) {
		this.opposingCoalitionEdges = list;
	}
	
	public List<SignedIntegerEdge> getOppsingCoalitonEdges(){
		return opposingCoalitionEdges;
	}

	public double computeSimilarity(SignedIntegerCluster<V, E> secondCluster, NodeSimilarityFunction<V> function) {
		double currentValue;
		double maxValue = -1;
		Iterator<V> firstClusterNodes 	= this.cluster.getVertices().iterator();
		Iterator<V> secondClusterNodes;
		while(firstClusterNodes.hasNext()) {
			V current = firstClusterNodes.next();
			secondClusterNodes = secondCluster.cluster.getVertices().iterator();
			while(secondClusterNodes.hasNext()) {
				V secondClusterNode = secondClusterNodes.next();
				currentValue = function.computeSimilarity(current, secondClusterNode);
				if(currentValue > maxValue) {
					maxValue = currentValue;
				}
			}
		}
		return maxValue;
	}
}
