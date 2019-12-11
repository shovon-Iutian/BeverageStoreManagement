/**
 *
 */
package de.uniba.dsg.dsam.client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.dsg.dsam.customexceptions.BeverageException;
import de.uniba.dsg.dsam.customexceptions.OrderException;
import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.CustomerOrder;
import de.uniba.dsg.dsam.persistence.BeverageManagement;

/**
 * @author Mohammed Mehedi Hasan
 *
 */
public class OrderJMSQueueServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(OrderJMSQueueServlet.class.getName());

	@EJB
	OrderJMSQueueSender sender;

	@EJB
	private BeverageManagement<?, Beverage> beverageManagement;

	/**
	 * Default constructor.
	 */
	public OrderJMSQueueServlet() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * doGET method passes on the request dispatcher to the appropriate jsp page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.setLevel(Level.ALL);
		logger.info("Initiated doGet() ");
		// get all available beverages for a new order
		try {
			refreshList(request, response);
		} catch (BeverageException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		request.getRequestDispatcher("/createOrder.jsp").forward(request, response);
	}

	/*
	 * refreshList method fetches data from database about the available beverages
	 * so that customers can choose from all the available options from UI.
	 */
	private void refreshList(HttpServletRequest request, HttpServletResponse response) throws BeverageException {

		List<Beverage> availableBeverageList = new ArrayList<Beverage>();
		availableBeverageList = beverageManagement.getAvailableBeverages();
		logger.info("Fetching a list of all available beverages");
		request.setAttribute("availableBeverageList", availableBeverageList);

	}

	/**
	 * doPOST method collects an order from the jsp page and send an object message to the message driven bean
	 * using the JMS Queue.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.setLevel(Level.ALL);
		logger.info("doPost method initiated.");
		ArrayList<Beverage> selectedItemList = new ArrayList<>();
		String[] beverageIds = request.getParameterValues("selectedBeverage");
		try {
			if (beverageIds.length == 0) {
				throw new OrderException("No items selected while creating the order!");
			}
		} catch (OrderException e) {
			logger.warning(e.getMessage());

		}
		if (beverageIds.length > 0) {
			logger.info("Beverage ids received");
			for (int i = 0; i < beverageIds.length; i++) {
				String id = beverageIds[i];
				String countId = id + "_quantity";
				String itemcount = request.getParameter(countId);
				try {
					if (itemcount == null)
						throw new OrderException("No quantity selection made while creating order");
				} catch (OrderException e) {
					logger.log(Level.WARNING, e.getMessage());
				}

				String[] count = itemcount.split("_");
				int quantity = Integer.parseInt(count[0]);

				if (quantity > 0) {

					Beverage beverage = beverageManagement.getOneBeverage(Integer.parseInt(beverageIds[i]));

					selectedItemList.add(beverage);

				}
			}
			logger.info("Creating a new customer order");
			CustomerOrder customerOrder = new CustomerOrder();
			customerOrder.setIssueDate(new Date());
			customerOrder.setOrderItems(selectedItemList);
			logger.info("Sending the customer order to the queue");
			sender.sendMessage(customerOrder);
		}
		//request.setAttribute("selectedItems", selectedItemList);
		response.sendRedirect("/frontend");
		//request.getRequestDispatcher("/confirmOrder.jsp").forward(request, response);

	}

}
