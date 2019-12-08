package de.uniba.dsg.dsam.model;

public abstract class Incentive extends AbstractDto {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Incentive{" +
				"name='" + name + '\'' +
				", id=" + id +
				'}';
	}
}
