package de.uniba.dsg.dsam.model;

import java.util.Date;
import java.util.List;

public class CustomerOrder extends AbstractDto {

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
