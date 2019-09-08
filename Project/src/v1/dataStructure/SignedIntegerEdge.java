package v1.dataStructure;

import v1.interfaces.SignedEdge;

public class SignedIntegerEdge implements  SignedEdge<Integer> {
	
	private String label;
	private Integer value;
	
	public SignedIntegerEdge(Integer value) {
		
		this.label = "-";
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public Integer returnSignedValue() {
		return value;
	}
	
	

}
