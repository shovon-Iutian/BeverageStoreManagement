package de.uniba.dsg.dsam.client;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.persistence.BeverageManagement;

/**
 * @author Mohammed Mehedi Hasan
 *
 */
public class OrderJMSQueueServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(OrderJMSQueueServlet.class.getName());
	
	@EJB
	private OrderJMSQueueSender sender;
	
	@EJB
	private BeverageManagement<?, Beverage> beverageManagement;
	
	/**
	 * Default constructor.
	 */
	public OrderJMSQueueServlet() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Beverage> beverages = beverageManagement.getAll();
		request.getSession().setAttribute("beverages", beverages);
		request.getRequestDispatcher("/jmsqueue.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String date = req.getParameter("issue_date").trim();
		int amount = Integer.valueOf(req.getParameter("beverage_amount").trim());
		int item = Integer.valueOf(req.getParameter("beverage_name").trim());
		Date date1 = null;
		int order_id = (int) ((Math.random() * ((5000 - 50) + 1)) + 50);
		
		try {
			date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			logger.info("Unable to parse string into date: " + e);
		}
		
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.setOrder_id(order_id);
		customerOrder.setIssueDate(date1);
		if(item != -1) {
			Optional<Beverage> beverage = beverageManagement.getAll().stream().filter(bever -> bever.getId() == item).findAny();
			beverage.ifPresent(customerOrder::setOrderItems);
		}
		customerOrder.setOrderAmount(amount);
		
		sender.sendMessage(customerOrder);
		
		resp.sendRedirect("/frontend");
	}
}
