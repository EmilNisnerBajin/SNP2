package v1.utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Pair;
import v1.dataStructure.SignedIntegerEdge;
import v1.interfaces.MyCloneable;
import v1.nodeSimilarityFunction.NodeSimilarityFunction;

public class Dendrogram<V extends MyCloneable, E extends SignedIntegerEdge> {
	
	private LinkedList<DendrogramPartition<V>> dendrogramPartitions;
	
	private DendrogramPartition<V> bestPartition;
	
	private UndirectedSparseGraph<V,E> graph;

	public Dendrogram() {
		this.dendrogramPartitions = new LinkedList<>();
	}
	
	public Dendrogram(UndirectedSparseGraph<V,E> graph) {
		this();
		this.graph = graph;
		divisiveConstruction();
	}
	
	public Dendrogram(UndirectedSparseGraph<V,E> graph, NodeSimilarityFunction<V> function) {
		this();
		this.graph = graph;
		agglomerativeConstruction(function);
	}
	
	// This method constructs initial partitions where 1node = 1cluster in agglomerative approach
	private void generateInitialPartitions() {
		LinkedList<NodeSetCluster<V>> initialListOfClusters = new LinkedList<>();
		Iterator<V> nodeIterator = this.graph.getVertices().iterator();
		while(nodeIterator.hasNext()) {
			V node = nodeIterator.next();
			NodeSetCluster<V> newCluster = new NodeSetCluster<>();
			newCluster.add(node);
			initialListOfClusters.add(newCluster);
		}
		
		DendrogramPartition<V> firstPartition = new DendrogramPartition<>(initialListOfClusters,1);
		dendrogramPartitions.addFirst(firstPartition);
	}
	
	private void divisiveConstruction() {
		return;
	}
	
	/*
	 * This method constructs dendrogram using agglomerative approach
	 * for each partition level it finds two clusters that are most
	 * similar and combines them.
	 */
	private void agglomerativeConstruction(NodeSimilarityFunction<V> function) {
		generateInitialPartitions();
		List<NodeSetCluster<V>> listOfClusters = dendrogramPartitions.getFirst().getClusters();
		int currentLevel = 1;
		SimilarityComputer<V,E> similarityComputer = new SimilarityComputer<>(function,listOfClusters);
		while(listOfClusters.size() > 1) {
			similarityComputer.computeSimilarityBetweenClusters();
			Pair<NodeSetCluster<V>> pair = similarityComputer.getPairWithMaxSimilarity();
			listOfClusters = combineClusters(pair, listOfClusters);
			DendrogramPartition<V> newPartition = new DendrogramPartition<>(listOfClusters, currentLevel++);
			this.dendrogramPartitions.addLast(newPartition);
			similarityComputer.setClusters(listOfClusters);
		}
	}
	
	public DendrogramPartition<V> getBestPartition(){
		return this.bestPartition;
	}
	
	// This method gets list of clusters that represents one partition in dendrogram, it combines two clusters
	// that are most similar and then return a copy of a inputed list where the pair with most similarity has 
	// been combined.
	private LinkedList<NodeSetCluster<V>> combineClusters(Pair<NodeSetCluster<V>> pair, List<NodeSetCluster<V>> set) {
		LinkedList<NodeSetCluster<V>> result 	= new LinkedList<>();
		NodeSetCluster<V> first 		= pair.getFirst();
		NodeSetCluster<V> second 		= pair.getSecond();
		for(NodeSetCluster<V> cluster : set) {
			if(!cluster.equals(first) && !cluster.equals(second)) {
				result.add((NodeSetCluster<V>) cluster.clone());
			}
		}
		NodeSetCluster<V> combinedOne = first.clone();
		combinedOne.addFromSet(second);
		
		result.add(combinedOne);
		return result;
	}
}
