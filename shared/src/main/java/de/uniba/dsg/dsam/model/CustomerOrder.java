package de.uniba.dsg.dsam.model;

import java.util.Date;

public final class CustomerOrder extends AbstractDto {
	
	private static final long serialVersionUID = 1L;
	
	private int order_id;
	private Date issueDate;
	private int orderAmount;
	private Beverage orderItems;

	/**
	 * @return the order_id
	 */
	public int getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	/**
	 * @return the issueDate
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate the issueDate to set
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return the orderAmount
	 */
	public int getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @return the orderItems
	 */
	public Beverage getOrderItems() {
		return orderItems;
	}

	/**
	 * @param orderItems the orderItems to set
	 */
	public void setOrderItems(Beverage orderItems) {
		this.orderItems = orderItems;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerOrder [issueDate=" + issueDate + ", orderAmount=" + orderAmount + ", orderItems=" + orderItems
				+ "]";
	}
}
