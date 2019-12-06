package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class IncentiveEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;

	@Version
	private int version;

	private String name;

	@OneToMany(mappedBy = "incentive", fetch = FetchType.LAZY, targetEntity = BeverageEntity.class)
	private Set<BeverageEntity> beverageEntities = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<BeverageEntity> getBeverageEntities() {
		return beverageEntities;
	}

	public void setBeverageEntities(Set<BeverageEntity> beverageEntities) {
		this.beverageEntities = beverageEntities;
	}

	@Override
	public String toString() {
		return "IncentiveEntity [id=" + id + ", version=" + version + ", name=" + name + "]";
	}

}
