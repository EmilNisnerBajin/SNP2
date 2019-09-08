package v1.utils;

import java.util.HashSet;
import java.util.Iterator;
import v1.interfaces.MyCloneable;
import v1.nodeSimilarityFunction.NodeSimilarityFunction;

public class NodeSetCluster<V extends MyCloneable> {

	private HashSet<V> set;
	
	public NodeSetCluster() {
		set = new HashSet<>();
	}
	
	public NodeSetCluster(HashSet<V> set) {
		this.set = set;
	}
	
	public boolean add(V node) {
		return set.add(node);
	}
	
	@SuppressWarnings("unchecked")
	public NodeSetCluster<V> clone(){
		HashSet<V> newSet = new HashSet<>();
		Iterator<V> nodes = this.set.iterator();
		while(nodes.hasNext()) {
			V current = nodes.next();
			V newNode = (V) current.clone();
			newSet.add(newNode);
		}
		NodeSetCluster<V> clonedObject = new NodeSetCluster<>(newSet);
		return clonedObject;
	}

	public double computeSimilarity(NodeSetCluster<V> secondCluster, NodeSimilarityFunction<V> function) {
		double currentValue;
		double maxValue = -1;
		Iterator<V> firstClusterNodes 	= this.set.iterator();
		Iterator<V> secondClusterNodes;
		while(firstClusterNodes.hasNext()) {
			V current = firstClusterNodes.next();
			secondClusterNodes = secondCluster.set.iterator();
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
	
	public void addFromSet(NodeSetCluster<V> secondSet) {
		Iterator<V> secondSetIterator = secondSet.set.iterator();
		while(secondSetIterator.hasNext()) {
			V current 		= secondSetIterator.next();
			@SuppressWarnings("unchecked")
			V clonedNode 	= (V) current.clone();
			this.set.add(clonedNode);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if(o == null || o.getClass() != this.getClass()) {
			return false;
		}
		@SuppressWarnings("unchecked")
		NodeSetCluster<V> n2 = (NodeSetCluster<V>) o;
		
		return this.set.containsAll(n2.set);
	}
	
}
