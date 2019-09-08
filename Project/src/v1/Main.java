package v1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import v1.dataStructure.Node;
import v1.dataStructure.SignedIntegerCluster;
import v1.dataStructure.SignedIntegerEdge;

public class Main {

	public static void main(String[] args) {
		/*
		 * SignedIntegerCavemanGraph<SignedIntegerEdge> generator = new
		 * SignedIntegerCavemanGraph<>(5,5);
		 * UndirectedSparseGraph<Node,SignedIntegerEdge> graph =
		 * generator.getCavemanGraph(); System.out.println(graph.getVertexCount());
		 * SignedIntegerComponent<Node,SignedIntegerEdge> comp = new
		 * SignedIntegerComponent<>(graph);
		 * System.out.println(comp.getClusters().size());
		 * System.out.println(comp.getListOfCoalitions().size());
		 */
		
		/*
		 * double [] randomArray =
		 * ProbabilityArrayGenerator.getRandomProbabilityArray(10);
		 */	
		
		
		Node n1 = new Node(1);
		Node n2 = n1.clone();
		
		HashSet<Node> s1 = new HashSet<>();
		s1.add(n1);
		
		
		LinkedList<HashSet<Node>> list1 = new LinkedList<>();
		list1.add(s1);
		
		System.out.println(list1.getFirst().size());
		
		LinkedList<HashSet<Node>> list2 = (LinkedList<HashSet<Node>>) list1.clone();
		list2.getFirst().remove(n2);
		
		System.out.println(list1.getFirst().size());
		System.out.println(list2.getFirst().size());
		
		
	}

}
