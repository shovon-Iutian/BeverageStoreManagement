package de.uniba.dsg.dsam.backend.beans;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import de.uniba.dsg.dsam.backend.entities.CustomerOrderEntity;
import de.uniba.dsg.dsam.model.CustomerOrder;

/**
 * Session Bean implementation class OrderJMSQueue
 * 
 * @author Mohammed Mehedi Hasan
 *
 */
@Stateless
@Local(OrderJMSQueueLocal.class)
public class OrderJMSQueue implements OrderJMSQueueLocal {

	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public OrderJMSQueue() {
    	
    }
    
	@Override
	public void insertOrder(CustomerOrder customerOrderDTO) {
		CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
		
		// Setting a issue date only for testing purpose
		customerOrderEntity.setIssueDate(customerOrderDTO.getIssueDate());
		
		entityManager.persist(customerOrderEntity);
	}

}
