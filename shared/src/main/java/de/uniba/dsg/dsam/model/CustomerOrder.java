package de.uniba.dsg.dsam.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public final class CustomerOrder extends AbstractDtoWithId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date issueDate;
    private List<Beverage> orderItems;

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public List<Beverage> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Beverage> orderItems) {
        this.orderItems = orderItems;
    }
}
