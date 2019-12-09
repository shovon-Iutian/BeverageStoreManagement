package de.uniba.dsg.dsam.backend.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "beverage")
public class BeverageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    @Version
    private int version;

    private String manufacturer;
    private String name;
    private int quantity;
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "incentive_id")
    private IncentiveEntity incentiveEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_order_id")
    private CustomerOrderEntity customerOrderEntity;

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public IncentiveEntity getIncentiveEntity() {
        return incentiveEntity;
    }

    public void setIncentiveEntity(IncentiveEntity incentive) {
        this.incentiveEntity = incentive;
    }

    public CustomerOrderEntity getCustomerOrderEntity() {
        return customerOrderEntity;
    }

    public void setCustomerOrderEntity(CustomerOrderEntity customerOrderEntity) {
        this.customerOrderEntity = customerOrderEntity;
    }
}
