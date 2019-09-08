package v1.testSamples;

import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import v1.dataStructure.Node;
import v1.dataStructure.SignedIntegerEdge;

public class StohasticSignedBlockModel<E extends SignedIntegerEdge> {
	
	private UndirectedSparseGraph<Node, SignedIntegerEdge> graph;
	private List<UndirectedSparseGraph<Node,SignedIntegerEdge>> listOfClusters;
	private double[] connectionProbabilityArray;
	private int numberOfCLusters;
	private int numberOfNodes;
	
	
	public StohasticSignedBlockModel(int numberOfNodes, int numberOfClusters, double[] clusterProbabilityArray) {
		if(numberOfClusters != clusterProbabilityArray.length) {
			throw new IllegalArgumentException("Wrong Input! Size of array of probability and number of cluster don't match!");
		}
		initializeFields(numberOfNodes, numberOfClusters, clusterProbabilityArray);
		fillGraph();
		connectNodesInCLusters();
		connectClusters();
	}
	
	private void initializeFields(int numberOfNodes, int numberOfClusters, double[] clusterProbabilityArray) {
		this.numberOfNodes 				= numberOfNodes;
		this.numberOfCLusters 			= numberOfClusters;
		this.connectionProbabilityArray = clusterProbabilityArray;
		this.graph						= new UndirectedSparseGraph<>();
		this.listOfClusters				= new LinkedList<>();
	}
	
	private void fillGraph() {
		// TODO Auto-generated method stub
		
	}

	private void connectNodesInCLusters() {
		// TODO Auto-generated method stub
		
	}

	private void connectClusters() {
		// TODO Auto-generated method stub
		
	}
	
}
