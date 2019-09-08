package v1.testSamples;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import v1.dataStructure.Node;
import v1.dataStructure.SignedIntegerEdge;

public class SignedIntegerCavemanGraph<E extends SignedIntegerEdge> {
	
	private UndirectedSparseGraph<Node,SignedIntegerEdge> graph;
	private Node[][] clusters;
	
	public SignedIntegerCavemanGraph(int numberOfClusters, int nodesInCluster) {
		
		if(numberOfClusters <= 0 || nodesInCluster <= 0) {
			throw new IllegalArgumentException("Error, Wrong input");
		}
		
		clusters 	= new Node[numberOfClusters][nodesInCluster];
		graph		= new UndirectedSparseGraph<>();
		
		addNodes();
		connectNodesInCluster();
		connectClusters();
	}
	
	private void addNodes() {
		int nodeLabel = 0;
		Node newNode;
		for(int i=0; i<clusters.length;i++) {
			for(int j=0 ; j<clusters[i].length; j++) {
				newNode = new Node(nodeLabel++);
				clusters[i][j] = newNode;
				graph.addVertex(newNode);
			}
		}
	}

	private void connectNodesInCluster() {
		SignedIntegerEdge e;
		Node first, second;
		for(int i=0; i<clusters.length;i++) {
			for(int j=0; j<clusters[i].length-1;j++) {
				e = new SignedIntegerEdge(5);
				first 	= clusters[i][j];
				second 	= clusters[i][j+1];
				graph.addEdge(e, first,second);
			}
		}
		
	}

	private void connectClusters() {
		SignedIntegerEdge e;
		Node first, second;
		int j = clusters.length-1;
		for(int i=0; i<clusters.length-1;i++) {
			e = new SignedIntegerEdge(-5);
			first 	= clusters[i][j];
			second 	= clusters[i+1][j];
			graph.addEdge(e, first,second);
		}
		e = new SignedIntegerEdge(-5);
		first = clusters[0][j];
		second = clusters[clusters.length-1][j];
		graph.addEdge(e, first, second);
		
	}

	public UndirectedSparseGraph<Node,SignedIntegerEdge> getCavemanGraph(){
		return graph;
	}


}
