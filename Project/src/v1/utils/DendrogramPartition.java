package v1.utils;

import java.util.List;
import v1.interfaces.MyCloneable;

public class DendrogramPartition<V extends MyCloneable> {

	private int partitionLevel;
	
	private List<NodeSetCluster<V>> clusters;
	
	public DendrogramPartition(List<NodeSetCluster<V>> list, int level) {
		this.clusters		 		= list;
		this.partitionLevel			= level;
	}
	
	public List<NodeSetCluster<V>> getClusters() {
		return this.clusters;
	}
	
	public int getPartitionLevel() {
		return this.partitionLevel;
	}
	
	
}
