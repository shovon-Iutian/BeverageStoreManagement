package de.uniba.dsg.dsam.backend.beans;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import de.uniba.dsg.dsam.backend.abstractbean.AbstractCrudBean;
import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.CustomerOrderEntity;
import de.uniba.dsg.dsam.backend.service.CrudService;
import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.persistence.BeverageManagement;
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
	
	@EJB
	private CrudService<BeverageEntity> beverageService;
	
	@EJB
	private BeverageManagement<BeverageEntity, Beverage> beverageManagement;
	
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
		BeverageEntity beverageEntity = customerOrderEntity.getBeverageEntity();
		if(beverageEntity != null) {
			Beverage beverage = this.beverageManagement.converEntityToDTO(beverageEntity);
			customerOrder.setOrderItems(beverage);
		}
		customerOrder.setIssueDate(customerOrderEntity.getIssueDate());
		customerOrder.setOrderAmount(customerOrderEntity.getOrderAmount());
		return customerOrder;
	}

	@Override
	protected CustomerOrderEntity convertDTOToEntity(CustomerOrder customerOrder) {
		CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
		customerOrderEntity.setBeverageEntity(beverageService.getOne(BeverageEntity.class, customerOrder.getOrderItems().getId()));
		customerOrderEntity.setId(customerOrder.getId());
		customerOrderEntity.setVersion(customerOrder.getVersion());
		customerOrderEntity.setIssueDate(customerOrder.getIssueDate());
		customerOrderEntity.setOrderAmount(customerOrder.getOrderAmount());
		return customerOrderEntity;
	}

}
