package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class CustomerOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Version
    private int version;

    @OneToMany(mappedBy = "customerOrderEntity", fetch = FetchType.LAZY, targetEntity = BeverageEntity.class)
    private List<BeverageEntity> beverageEntities = new ArrayList<>();

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

    public List<BeverageEntity> getBeverageEntities() {
        return beverageEntities;
    }

    public void setBeverageEntities(List<BeverageEntity> beverageEntities) {
        this.beverageEntities = beverageEntities;
    }
}
