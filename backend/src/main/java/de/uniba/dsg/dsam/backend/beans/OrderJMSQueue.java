package de.uniba.dsg.dsam.backend.beans;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import de.uniba.dsg.dsam.backend.abstractbean.AbstractCrudBean;
import de.uniba.dsg.dsam.backend.entities.CustomerOrderEntity;
import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.persistence.OrderJMSQueueManagement;

/**
 * Session Bean implementation class OrderJMSQueue
 * 
 * @author Mohammed Mehedi Hasan
 *
 */
@Stateless
@Remote(OrderJMSQueueManagement.class)
public class OrderJMSQueue extends AbstractCrudBean<CustomerOrderEntity, CustomerOrder> 
	implements OrderJMSQueueManagement<CustomerOrder> {
	
    /**
     * Default constructor. 
     */
    public OrderJMSQueue() { 
    	this.persistentClass = CustomerOrderEntity.class;
    }

	@Override
	protected CustomerOrder converEntityToDTO(CustomerOrderEntity customerOrderEntity) {
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setId(customerOrderEntity.getId());
		customerOrder.setVersion(customerOrderEntity.getVersion());
		customerOrder.setIssueDate(customerOrderEntity.getIssueDate());
		customerOrder.setOrderAmount(customerOrderEntity.getOrderAmount());
		return customerOrder;
	}

	@Override
	protected CustomerOrderEntity convertDTOToEntity(CustomerOrder customerOrder) {
		CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
		customerOrderEntity.setId(customerOrder.getId());
		customerOrderEntity.setVersion(customerOrder.getVersion());
		customerOrderEntity.setIssueDate(customerOrder.getIssueDate());
		customerOrderEntity.setOrderAmount(customerOrder.getOrderAmount());
		return customerOrderEntity;
	}

}
