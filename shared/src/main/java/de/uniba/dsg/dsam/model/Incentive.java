package de.uniba.dsg.dsam.model;

public abstract class Incentive {
	
	private String name;
	
	private IncentiveType incentiveType;

	public IncentiveType getIncentiveType() {
		return incentiveType;
	}

	public void setIncentiveType(IncentiveType incentiveType) {
		this.incentiveType = incentiveType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
