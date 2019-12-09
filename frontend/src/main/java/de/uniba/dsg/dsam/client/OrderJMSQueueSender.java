package de.uniba.dsg.dsam.client;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import de.uniba.dsg.dsam.model.CustomerOrder;

/**
 * 
 * @author Mohammed Mehedi Hasan
 *
 */
@Stateless
public class OrderJMSQueueSender {
    private static final Logger logger = Logger.getLogger(OrderJMSQueueSender.class.getName());

    @Resource(mappedName = "BeverageStoreCF")
    private ConnectionFactory factory;

    @Resource(mappedName = "BeverageStoreQueue")
    private Queue target;
    
    /**
	 * Default constructor
	 */
	public OrderJMSQueueSender() {
		
	}
	
    /**
     * Implementing sendMessage functionality.
     * Sends message to JMS queue.
     * 
     * @param customerOrder the DTO containing all the order details.
     */
    public void sendMessage(CustomerOrder customerOrder) {
        try (Connection connection = factory.createConnection()) {
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer = session.createProducer(target);
            ObjectMessage message = session.createObjectMessage(customerOrder);
            producer.send(message);
            logger.info("Sent order " + customerOrder + " to JMS queue");
        } catch (JMSException ex) {
            logger.severe("Could not send message to Queue" + ex);
        }
    }
}
