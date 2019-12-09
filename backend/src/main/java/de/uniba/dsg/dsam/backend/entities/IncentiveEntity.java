package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "incentive")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
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

	@Column(name="dtype", insertable = false, updatable = false)
	private String dtype;

	@OneToMany(mappedBy = "incentiveEntity", fetch = FetchType.LAZY, targetEntity = BeverageEntity.class)
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

	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public void setBeverageEntities(Set<BeverageEntity> beverageEntities) {
		this.beverageEntities = beverageEntities;
	}

	@Override
	public String toString() {
		return "IncentiveEntity [id=" + id + ", version=" + version + ", name=" + name + "]";
	}

}
