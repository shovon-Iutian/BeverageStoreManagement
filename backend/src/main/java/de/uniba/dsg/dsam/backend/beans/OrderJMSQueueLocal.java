package de.uniba.dsg.dsam.backend.beans;

import de.uniba.dsg.dsam.model.CustomerOrder;

/**
 * Simple interface for a session bean that is not to be exposed to the outside world.
 * 
 * @author Mohammed Mehedi Hasan
 *
 */
public interface OrderJMSQueueLocal {
	/**
	 * Adds a new order into the database.
	 * 
	 * @param customerOrder DTO consists of order details.
	 */
	public void insertOrder(CustomerOrder customerOrder);
}
