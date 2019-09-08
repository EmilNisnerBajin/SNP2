package v1.dataStructure;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

// This class represents one component in graph
public class SignedIntegerComponent<V , E extends SignedIntegerEdge> {
	
	private HashSet<V> visited;
	private List<SignedIntegerCluster<V,SignedIntegerEdge>> listOfCoalitions;
	private List<SignedIntegerCluster<V,SignedIntegerEdge>> listOfNotCoalitions;
	private List<SignedIntegerCluster<V,SignedIntegerEdge>> clusters;
	private UndirectedSparseGraph<V,SignedIntegerEdge> 		component;
	
	public SignedIntegerComponent(UndirectedSparseGraph<V,SignedIntegerEdge> graph) {
		this.component 				= graph;
		this.listOfCoalitions 		= new LinkedList<>();
		this.listOfNotCoalitions 	= new LinkedList<>();
		this.clusters				= new LinkedList<>();
		this.visited				= new HashSet<>();
		detectClusters();
		checkClusters();
		fillClusterLists();
	}


	private void detectClusters() {
		Iterator<V> iterator = component.getVertices().iterator();
		while(iterator.hasNext()) {
			V node = iterator.next();
			if(!visited.contains(node)) {
				identifyCluster(node);
			}
		}
	}
	
	private void identifyCluster(V node) {
		LinkedList<V> queue = new LinkedList<>();
		queue.add(node);
		visited.add(node);
		UndirectedSparseGraph<V,SignedIntegerEdge> cluster = new UndirectedSparseGraph<>();
		
		while(!queue.isEmpty()) {
			V  current = queue.removeFirst();
			cluster.addVertex(current);
			Iterator<V> nodeIterator = component.getNeighbors(current).iterator();
			while(nodeIterator.hasNext()) {
				V neighbour = nodeIterator.next();
				if(!visited.contains(neighbour)) {
					SignedIntegerEdge e = component.findEdge(current, neighbour);
					if(e.returnSignedValue()>0) {
						visited.add(neighbour);
						cluster.addVertex(neighbour);
						cluster.addEdge(e,current,neighbour);
						queue.addLast(neighbour);
					}
				}
			}
		}
		clusters.add(new SignedIntegerCluster<V,SignedIntegerEdge>(cluster));
	}
	
	// Check every cluster
	private void checkClusters() {
		for(SignedIntegerCluster<V,SignedIntegerEdge> cluster : clusters) {
			checkIfCoalition(cluster);
		}
	}

	// Check if cluster is coalition
	private void checkIfCoalition(SignedIntegerCluster<V, SignedIntegerEdge> cluster) {
		Iterator<V> nodesInCluster = cluster.getCluster().getVertices().iterator();
		while(nodesInCluster.hasNext()) {
			V currentNode = nodesInCluster.next();
			Iterator<V> neighboursIterator = cluster.getCluster().getNeighbors(currentNode).iterator();
			while(neighboursIterator.hasNext()) {
				V neighbour = neighboursIterator.next();
				SignedIntegerEdge e = component.findEdge(currentNode, neighbour);
				if(e !=null && e.returnSignedValue()<0) {
					if(cluster.isCoalition()) {
						cluster.setCoaliton(false);
					}
					cluster.getOppsingCoalitonEdges().add(e);
				}
			}
		}
	}

	// Fills the lists of clusters that are coalitions and the ones that aren't
	private void fillClusterLists() {
		for(SignedIntegerCluster<V,SignedIntegerEdge> cluster : clusters) {
			if(cluster.isCoalition()) {
				listOfCoalitions.add(cluster);
			}else {
				listOfNotCoalitions.add(cluster);
			}
		}
		
	}


	public List<SignedIntegerCluster<V, SignedIntegerEdge>> getListOfCoalitions() {
		return listOfCoalitions;
	}


	public List<SignedIntegerCluster<V, SignedIntegerEdge>> getListOfNotCoalitions() {
		return listOfNotCoalitions;
	}


	public List<SignedIntegerCluster<V, SignedIntegerEdge>> getClusters() {
		return clusters;
	}
}
